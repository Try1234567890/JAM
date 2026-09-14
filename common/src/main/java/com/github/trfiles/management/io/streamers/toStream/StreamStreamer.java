package com.github.trfiles.management.io.streamers.toStream;

import com.github.trfiles.management.io.streamers.InstanceStreamer;
import com.github.trfiles.management.io.streamers.StreamerConfiguration;
import com.github.utilities.options.BooleanOption;

import java.io.IOException;
import java.io.OutputStream;

public abstract class StreamStreamer<I> extends InstanceStreamer<I, OutputStream> {

    protected StreamStreamer(I source, OutputStream destination) {
        super(source, destination);
    }

    /**
     * Effectively performs the chunk-by-chunk read/write loop, writing each chunk read from
     * {@code source} directly into the {@code destination} stream.
     */
    protected abstract void streamToStream(I source, OutputStream destination, long offset, long length) throws Exception;

    @Override
    protected final void streamEffectively(I source, OutputStream destination, long offset, long length) throws Exception {
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
        this.streamToStream(source, destination, offset, length);
    }

    @Override
    public abstract StreamConfiguration<I> getConfiguration();

    public static class StreamConfiguration<I> extends StreamerConfiguration<I, OutputStream> {
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
