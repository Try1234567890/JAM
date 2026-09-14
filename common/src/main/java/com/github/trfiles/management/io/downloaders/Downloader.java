package com.github.trfiles.management.io.downloaders;

import org.jetbrains.annotations.NotNull;

/**
 * A {@code Downloader} is the base interface for all systems that copy a remote {@code source}
 * (identified, in the concrete implementations of this package, by a {@link java.net.URI}) into a
 * local {@code destination}, reading it in successive chunks and writing each chunk immediately —
 * the same "constant memory footprint" idea as {@link com.github.trfiles.management.io.streamers.Streamer},
 * applied to a source that is remote and only reachable over the network instead of local.
 * <p>
 * <b>Unlike {@link com.github.trfiles.management.io.streamers.Streamer#stream}/
 * {@link com.github.trfiles.management.io.writers.Writer#write}, {@link #download} DOES rethrow</b> the
 * exception raised while downloading (see {@code InstanceDownloader} and
 * {@code 04-conventions-and-gotchas.md}): a download that silently "succeeds" empty/truncated, or whose
 * scheme/size-limit rejection got swallowed, is a correctness and security foot-gun a caller relying on
 * {@code try/catch} should never have to guard against separately.
 *
 * @param <I> the source of the process (a {@link java.net.URI} in every implementation currently in this
 *            package).
 * @param <D> the destination of the process.
 */
public interface Downloader<I, D> {

    static DownloaderBuilder builder() {
        return DownloaderBuilder.get();
    }

    /**
     * Sentinel value for the {@code length} parameter meaning "download until the source is exhausted",
     * instead of stopping at a specific, pre-determined amount of bytes.
     */
    long UNBOUNDED = -1L;

    /**
     * Downloads the sub-value of {@code source}, peeked from the {@code offset} index (inclusive) and the
     * {@code length} index (exclusive), into {@code destination}, reading and writing it a chunk at a
     * time. A {@code length} of {@link #UNBOUNDED} downloads the source until it is exhausted.
     *
     * @param source      The remote source to download.
     * @param destination The destination the source is downloaded into.
     * @param offset      The start index of the sub-value peeked from {@code source} (inclusive).
     * @param length      The end index of the sub-value peeked from {@code source} (exclusive), or
     *                    {@link #UNBOUNDED}.
     * @throws Exception If an error occurs while downloading, including a rejected scheme, a redirect loop,
     *                    a response status the caller must handle, or the configured size limit being
     *                    exceeded.
     */
    void download(@NotNull I source, @NotNull D destination, long offset, long length) throws Exception;

    /**
     * Downloads the sub-value of {@code source}, peeked from the {@code offset} index (inclusive) until
     * the source is exhausted, into {@code destination}.
     */
    void download(@NotNull I source, @NotNull D destination, long offset) throws Exception;

    /**
     * Downloads the sub-value of {@code source}, peeked until the index {@code length} (exclusive) is
     * reached, into {@code destination}.
     */
    void downloadUntil(@NotNull I source, @NotNull D destination, long length) throws Exception;

    /**
     * Downloads the whole {@code source} into {@code destination}, until the source is exhausted.
     */
    void download(@NotNull I source, @NotNull D destination) throws Exception;

    /**
     * Same as {@link #download(Object, Object, long, long)}, but any {@link Exception} thrown is silently
     * ignored.
     */
    default void downloadSilently(@NotNull I source, @NotNull D destination, long offset, long length) {
        try {
            download(source, destination, offset, length);
        } catch (Exception _) {
        }
    }

    /**
     * Same as {@link #download(Object, Object, long)}, but any {@link Exception} thrown is silently
     * ignored.
     */
    default void downloadSilently(@NotNull I source, @NotNull D destination, long offset) {
        try {
            download(source, destination, offset);
        } catch (Exception _) {
        }
    }

    /**
     * Same as {@link #downloadUntil(Object, Object, long)}, but any {@link Exception} thrown is silently
     * ignored.
     */
    default void downloadUntilSilently(@NotNull I source, @NotNull D destination, long length) {
        try {
            downloadUntil(source, destination, length);
        } catch (Exception _) {
        }
    }

    /**
     * Same as {@link #download(Object, Object)}, but any {@link Exception} thrown is silently ignored.
     */
    default void downloadSilently(@NotNull I source, @NotNull D destination) {
        try {
            download(source, destination);
        } catch (Exception _) {
        }
    }
}