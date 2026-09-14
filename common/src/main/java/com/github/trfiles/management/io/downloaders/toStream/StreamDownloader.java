package com.github.trfiles.management.io.downloaders.toStream;

import com.github.trfiles.management.io.downloaders.DownloaderConfiguration;
import com.github.trfiles.management.io.downloaders.InstanceDownloader;
import com.github.utilities.options.BooleanOption;

import java.io.IOException;
import java.io.OutputStream;

/**
 * Base class for downloaders whose destination is an already opened {@link OutputStream}. This is where
 * the real "read the response, write it out" logic lives for every source type (mirrors
 * {@code StreamStreamer}, which plays the same role for {@code streamers}): a concrete subclass only has
 * to implement {@link #downloadToStream}, and gets the {@code flushStream}/{@code closeStream}
 * post-process wiring below for free.
 *
 * @param <I> the source of the process (a {@link java.net.URI} in every implementation currently in this
 *            package).
 */
public abstract class StreamDownloader<I> extends InstanceDownloader<I, OutputStream> {

    protected StreamDownloader(I source, OutputStream destination) {
        super(source, destination);
    }

    /**
     * Effectively performs the request and the chunk-by-chunk read/write loop, writing each chunk read
     * from the response directly into the {@code destination} stream.
     */
    protected abstract void downloadToStream(I source, OutputStream destination, long offset, long length) throws Exception;

    @Override
    protected final void downloadEffectively(I source, OutputStream destination, long offset, long length) throws Exception {
        if (getConfiguration().shouldFlushStream()) {
            getConfiguration().newOnPostProcess((_, out) -> {
                try {
                    out.flush();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        }
        if (getConfiguration().shouldCloseStream()) {
            getConfiguration().newOnPostProcess((_, out) -> {
                try {
                    out.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        }
        this.downloadToStream(source, destination, offset, length);
    }

    @Override
    public abstract StreamConfiguration<I> getConfiguration();

    public static class StreamConfiguration<I> extends DownloaderConfiguration<I, OutputStream> {
        private final BooleanOption FLUSH_STREAM = new BooleanOption(true);
        private final BooleanOption CLOSE_STREAM = new BooleanOption(true);

        public StreamConfiguration<I> setFlushStream(boolean state) {
            FLUSH_STREAM.set(state);
            return this;
        }

        public StreamConfiguration<I> flushStream() {
            return setFlushStream(true);
        }

        public StreamConfiguration<I> notFlushStream() {
            return setFlushStream(false);
        }

        public boolean shouldFlushStream() {
            return FLUSH_STREAM.get();
        }

        public StreamConfiguration<I> setCloseStream(boolean state) {
            CLOSE_STREAM.set(state);
            return this;
        }

        public StreamConfiguration<I> closeStream() {
            return setCloseStream(true);
        }

        public StreamConfiguration<I> notCloseStream() {
            return setCloseStream(false);
        }

        public boolean shouldCloseStream() {
            return CLOSE_STREAM.get();
        }
    }
}
