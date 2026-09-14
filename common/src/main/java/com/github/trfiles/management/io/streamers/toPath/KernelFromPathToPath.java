package com.github.trfiles.management.io.streamers.toPath;

import com.github.trfiles.management.io.streamers.InstanceStreamer;
import com.github.trfiles.management.io.streamers.StreamerConfiguration;

import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

/**
 * Streams a {@link Path} into another {@link Path}, reading the source through a {@link FileChannel}
 * instead of a regular {@link java.io.InputStream}.
 * <p>
 * Unlike {@link FromPathToPath}, which moves the file content through a user-space buffer, this
 * implementation delegates the transfer to the operating system kernel via
 * {@link FileChannel#transferTo(long, long, java.nio.channels.WritableByteChannel)} (the {@code sendfile}
 * system call, where the platform supports it). The bytes never have to be copied into the JVM heap at
 * all, which makes this the implementation to prefer when copying very large - potentially "infinite" -
 * files between two local paths.
 * <p>
 * A single call to {@code transferTo} is never guaranteed to transfer the whole requested range in one
 * shot, so the transfer is performed in a loop, one kernel-sized chunk (see
 * {@link KernelFromPathToPathConfiguration#DEFAULT_KERNEL_CHUNK_SIZE}) at a time, until the whole range
 * has been copied or the source is exhausted - which is exactly what allows this class to cope with
 * files of any size without ever materializing their content in memory.
 */
public class KernelFromPathToPath extends InstanceStreamer<Path, Path> {
    private KernelFromPathToPathConfiguration configuration;

    private KernelFromPathToPath(Path source, Path destination, KernelFromPathToPathConfiguration configuration) {
        super(source, destination);
        this.configuration = configuration != null ? configuration : new KernelFromPathToPathConfiguration();
    }

    public static KernelFromPathToPath newInstance(Path source, Path destination) {
        return new KernelFromPathToPath(source, destination, new KernelFromPathToPathConfiguration());
    }

    public static KernelFromPathToPath newInstance(Path source, Path destination, KernelFromPathToPathConfiguration configuration) {
        return new KernelFromPathToPath(source, destination, configuration);
    }

    @Override
    protected void streamEffectively(Path source, Path destination, long offset, long length) throws Exception {
        checkWritable(destination);

        try (FileChannel in = FileChannel.open(source, StandardOpenOption.READ);
             FileChannel out = FileChannel.open(destination, StandardOpenOption.WRITE)) {

            long position = offset;
            // "length" is an exclusive end index (consistent with Writer's semantics), not a byte count:
            // the actual amount of bytes to transfer is "length - offset". UNBOUNDED (-1) keeps the loop
            // going until the source channel is exhausted.
            long remaining = length == UNBOUNDED ? UNBOUNDED : length - offset;
            long chunkSize = getConfiguration().getBufferSize();

            while (remaining == UNBOUNDED || remaining > 0) {
                long requested = remaining == UNBOUNDED ? chunkSize : Math.min(remaining, chunkSize);
                long transferred = in.transferTo(position, requested, out);

                if (transferred <= 0) {
                    // the source channel has no more bytes to offer: the transfer is complete.
                    break;
                }

                position += transferred;
                if (remaining != UNBOUNDED) {
                    remaining -= transferred;
                }
            }
        }
    }

    public KernelFromPathToPath withConfiguration(KernelFromPathToPathConfiguration configuration) {
        this.configuration = configuration;
        return this;
    }

    @Override
    public KernelFromPathToPathConfiguration getConfiguration() {
        return configuration;
    }

    @Override
    protected long _size(Path value) throws IOException {
        return Files.size(value);
    }

    public static class KernelFromPathToPathConfiguration extends StreamerConfiguration<Path, Path> {
        /**
         * For a kernel-level transfer the "buffer" is not a JVM byte array, but the maximum amount of
         * bytes requested from the kernel on every single {@code transferTo} call. A large value reduces
         * the number of system calls needed to copy very large files.
         */
        public static final int DEFAULT_KERNEL_CHUNK_SIZE = 64 * 1024 * 1024; // 64 MB

        public KernelFromPathToPathConfiguration() {
            setBufferSize(DEFAULT_KERNEL_CHUNK_SIZE);
        }
    }
}
