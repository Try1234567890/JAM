package com.github.trfiles.management.io.uploaders.fromStream;

import com.github.trfiles.management.io.uploaders.InstanceUploader;
import com.github.trfiles.management.io.uploaders.UploaderConfiguration;
import com.github.utilities.options.BooleanOption;

import java.io.IOException;
import java.io.InputStream;

/**
 * Base class for uploaders whose source is an already opened {@link InputStream}. This is where the real
 * "read the source, write it to the remote destination" logic lives for every destination type (mirrors
 * {@code StreamDownloader}, which plays the same role for {@code downloaders} on the opposite side of the
 * connection): a concrete subclass only has to implement {@link #uploadFromStream}, and gets the
 * {@code closeStream} post-process wiring below for free.
 * <p>
 * Unlike {@code StreamDownloader} (whose destination {@link java.io.OutputStream} is offered both
 * {@code flushStream} and {@code closeStream} post-process hooks, since flushing a destination the caller
 * still owns is meaningful), a source {@link InputStream} only needs {@code closeStream}: there is nothing
 * to "flush" on the read side.
 *
 * @param <D> the destination of the process (a {@link java.net.URI} in every implementation currently in
 *            this package).
 */
public abstract class StreamUploader<D> extends InstanceUploader<InputStream, D> {

    protected StreamUploader(InputStream source, D destination) {
        super(source, destination);
    }

    /**
     * Effectively performs the connection and the chunk-by-chunk read/write loop, reading each chunk
     * directly from the {@code source} stream and writing it to the remote {@code destination}.
     */
    protected abstract void uploadFromStream(InputStream source, D destination, long offset, long length) throws Exception;

    @Override
    protected final void uploadEffectively(InputStream source, D destination, long offset, long length) throws Exception {
        if (getConfiguration().shouldCloseStream()) {
            getConfiguration().newOnPostProcess((in, _) -> {
                try {
                    in.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        }
        this.uploadFromStream(source, destination, offset, length);
    }

    /**
     * {@inheritDoc}
     * <p>
     * Always {@code -1}: the size of an already-opened {@link InputStream} generally cannot be known
     * without consuming it (unlike a local {@link java.nio.file.Path}, see {@code PathUploader#_size}).
     * This is the same value {@code InstanceDownloader#_size} always returns, but for a different reason —
     * here it is genuinely unknown, not distrusted.
     */
    @Override
    protected long _size(InputStream value) {
        return -1;
    }

    @Override
    public abstract StreamConfiguration<D> getConfiguration();

    public static class StreamConfiguration<D> extends UploaderConfiguration<InputStream, D> {
        private final BooleanOption CLOSE_STREAM = new BooleanOption(true);

        public StreamConfiguration<D> setCloseStream(boolean state) {
            CLOSE_STREAM.set(state);
            return this;
        }

        public StreamConfiguration<D> closeStream() {
            return setCloseStream(true);
        }

        public StreamConfiguration<D> notCloseStream() {
            return setCloseStream(false);
        }

        public boolean shouldCloseStream() {
            return CLOSE_STREAM.get();
        }
    }
}
