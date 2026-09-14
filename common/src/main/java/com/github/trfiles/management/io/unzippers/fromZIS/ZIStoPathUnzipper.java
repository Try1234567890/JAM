package com.github.trfiles.management.io.unzippers.fromZIS;

import com.github.trfiles.management.FileCreator;
import com.github.utilities.validators.Preconditions;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * Extracts the entries of an already opened {@link ZipInputStream} onto a
 * destination directory {@link Path}.
 * <p>
 * This is the only class in the {@code unzippers} package that actually
 * touches the filesystem while reading entries, and therefore the only one
 * that needs to defend against a maliciously crafted ZIP archive. Two
 * distinct classes of attack are mitigated:
 * <ul>
 *     <li><b>Zip Slip</b> (path traversal): an entry name such as
 *     {@code ../../etc/cron.d/evil} or an absolute path like
 *     {@code /etc/passwd} could otherwise make the extracted file land
 *     outside of {@code destination}. Every entry's target path is resolved
 *     against the (normalized, absolute) destination root and verified to
 *     still be contained within it <b>before</b> anything is written or any
 *     directory is created for it.</li>
 *     <li><b>Decompression bombs</b>: a tiny archive can expand to an
 *     enormous amount of data (or contain an enormous number of entries),
 *     exhausting disk space/memory. {@link ZIStoPathUnzipperConfiguration}
 *     exposes configurable ceilings on the number of entries, the
 *     uncompressed size of a single entry, and the cumulative uncompressed
 *     size of the whole archive; extraction is aborted with an
 *     {@link IOException} as soon as any of them is crossed &mdash; the
 *     checks are performed against the number of bytes actually read from
 *     the stream, not against the (attacker-controlled and therefore
 *     untrustworthy) {@link ZipEntry#getSize()} metadata.</li>
 * </ul>
 * Note: plain {@code java.util.zip} does not expose POSIX file permissions or
 * symbolic-link entries, so this class does not attempt to restore/follow
 * either — every entry is materialized as a plain file or directory.
 */
public class ZIStoPathUnzipper extends ZISUnzipper<Path> {
    private ZIStoPathUnzipperConfiguration configuration;

    protected ZIStoPathUnzipper(ZipInputStream input, Path destination, ZIStoPathUnzipperConfiguration configuration) {
        super(input, destination);
        this.configuration = configuration != null ? configuration : new ZIStoPathUnzipperConfiguration();
    }

    public static ZIStoPathUnzipper newInstance(ZipInputStream input, Path destination) {
        return new ZIStoPathUnzipper(input, destination, new ZIStoPathUnzipperConfiguration());
    }

    public static ZIStoPathUnzipper newInstance(ZipInputStream input, Path destination, ZIStoPathUnzipperConfiguration configuration) {
        return new ZIStoPathUnzipper(input, destination, configuration);
    }

    @Override
    public ZIStoPathUnzipperConfiguration getConfiguration() {
        return configuration;
    }

    public ZIStoPathUnzipper withConfiguration(ZIStoPathUnzipperConfiguration configuration) {
        this.configuration = configuration;
        return this;
    }

    @Override
    protected void unzipEffective(ZipInputStream input, Path destination, Predicate<ZipEntry> include) throws Exception {
        Path destinationRoot = prepareDestinationRoot(destination);
        ZIStoPathUnzipperConfiguration config = getConfiguration();

        long totalUncompressed = 0L;
        long entryCount = 0L;

        ZipEntry entry;
        while ((entry = input.getNextEntry()) != null) {
            entryCount++;
            if (entryCount > config.getMaxEntries()) {
                throw new IOException("Refusing to unzip: the archive contains more than " +
                        config.getMaxEntries() + " entries (possible zip bomb).");
            }

            if (!include.test(entry)) {
                input.closeEntry();
                continue;
            }

            Path entryDestination = resolveEntryDestination(destinationRoot, entry);

            if (entry.isDirectory()) {
                createDirectory(entryDestination);
                input.closeEntry();
                continue;
            }

            createDirectory(entryDestination.getParent());

            if (!config.isOverwriteExisting() && Files.exists(entryDestination)) {
                throw new IOException("Refusing to overwrite existing file while unzipping: " + entryDestination);
            }

            totalUncompressed += extractEntry(input, entry, entryDestination, config, totalUncompressed);
            input.closeEntry();
        }
    }

    /**
     * Resolves and creates (if needed) the destination directory, returning
     * its normalized, absolute form so that every entry can be safely
     * checked against it afterward.
     */
    private Path prepareDestinationRoot(Path destination) throws IOException {
        Path root = Preconditions.simpleParameterNotNull(destination, "destination").toAbsolutePath().normalize();

        FileCreator.Result result = FileCreator.newDirectories(root);
        if (!result.isSuccess()) {
            throw new IOException("Cannot create destination directory " + root, result.error());
        }
        return root;
    }

    /**
     * Resolves the entry name against {@code destinationRoot} and verifies
     * that the resulting path is still contained within it, rejecting any
     * entry that would otherwise escape the destination directory (Zip Slip).
     */
    private Path resolveEntryDestination(Path destinationRoot, ZipEntry entry) throws IOException {
        String name = entry.getName();
        Path resolved = destinationRoot.resolve(name).normalize();

        if (!resolved.equals(destinationRoot) && !resolved.startsWith(destinationRoot)) {
            throw new IOException("Zip entry \"" + name + "\" would be extracted outside of the destination " +
                    "directory \"" + destinationRoot + "\" (Zip Slip attempt): " + resolved);
        }
        return resolved;
    }

    private void createDirectory(Path directory) throws IOException {
        if (directory == null) return;

        FileCreator.Result result = FileCreator.newDirectories(directory);
        if (!result.isSuccess() && result.status() != FileCreator.Status.ALREADY_EXISTS) {
            throw new IOException("Cannot create directory " + directory, result.error());
        }
    }

    /**
     * Copies the current entry's content to {@code entryDestination},
     * counting bytes as they are read from the (already decompressing)
     * {@code input} stream, and aborting as soon as either the per-entry or
     * the cumulative uncompressed-size limit is crossed.
     *
     * @return the number of uncompressed bytes written for this entry.
     */
    private long extractEntry(ZipInputStream input, ZipEntry entry, Path entryDestination,
                               ZIStoPathUnzipperConfiguration config, long totalUncompressedSoFar) throws IOException {
        long maxEntrySize = config.getMaxEntrySize();
        long maxTotalSize = config.getMaxTotalUncompressedSize();
        byte[] buffer = new byte[config.getBufferSize()];

        long entryTotal = 0L;
        try (OutputStream os = Files.newOutputStream(entryDestination)) {
            int read;
            while ((read = input.read(buffer)) != -1) {
                entryTotal += read;

                if (entryTotal > maxEntrySize) {
                    throw new IOException("Refusing to unzip entry \"" + entry.getName() + "\": uncompressed size " +
                            "exceeds the configured limit of " + maxEntrySize + " bytes (possible zip bomb).");
                }
                if (totalUncompressedSoFar + entryTotal > maxTotalSize) {
                    throw new IOException("Refusing to unzip: total uncompressed size exceeds the configured limit " +
                            "of " + maxTotalSize + " bytes (possible zip bomb).");
                }

                os.write(buffer, 0, read);
            }
        }
        return entryTotal;
    }

    public static class ZIStoPathUnzipperConfiguration extends ZISUnzipperConfiguration<Path> {
        public static final long DEFAULT_MAX_ENTRIES = 10_000L;
        public static final long DEFAULT_MAX_ENTRY_SIZE = 1L << 30; // 1 GiB
        public static final long DEFAULT_MAX_TOTAL_UNCOMPRESSED_SIZE = 4L << 30; // 4 GiB
        public static final int DEFAULT_BUFFER_SIZE = 8192;

        private boolean overwriteExisting = true;
        private long maxEntries = DEFAULT_MAX_ENTRIES;
        private long maxEntrySize = DEFAULT_MAX_ENTRY_SIZE;
        private long maxTotalUncompressedSize = DEFAULT_MAX_TOTAL_UNCOMPRESSED_SIZE;
        private int bufferSize = DEFAULT_BUFFER_SIZE;

        public boolean isOverwriteExisting() {
            return overwriteExisting;
        }

        public ZIStoPathUnzipperConfiguration withOverwriteExisting(boolean overwriteExisting) {
            this.overwriteExisting = overwriteExisting;
            return this;
        }

        public long getMaxEntries() {
            return maxEntries;
        }

        public ZIStoPathUnzipperConfiguration withMaxEntries(long maxEntries) {
            this.maxEntries = maxEntries;
            return this;
        }

        public long getMaxEntrySize() {
            return maxEntrySize;
        }

        public ZIStoPathUnzipperConfiguration withMaxEntrySize(long maxEntrySize) {
            this.maxEntrySize = maxEntrySize;
            return this;
        }

        public long getMaxTotalUncompressedSize() {
            return maxTotalUncompressedSize;
        }

        public ZIStoPathUnzipperConfiguration withMaxTotalUncompressedSize(long maxTotalUncompressedSize) {
            this.maxTotalUncompressedSize = maxTotalUncompressedSize;
            return this;
        }

        public int getBufferSize() {
            return bufferSize;
        }

        public ZIStoPathUnzipperConfiguration withBufferSize(int bufferSize) {
            this.bufferSize = bufferSize > 0 ? bufferSize : DEFAULT_BUFFER_SIZE;
            return this;
        }
    }
}
