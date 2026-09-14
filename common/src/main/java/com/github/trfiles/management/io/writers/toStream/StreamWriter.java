package com.github.trfiles.management.io.writers.toStream;

import com.github.trfiles.management.io.writers.InstanceWriter;
import com.github.trfiles.management.io.writers.WriterConfiguration;
import com.github.utilities.options.BooleanOption;

import java.io.IOException;
import java.io.OutputStream;

public abstract class StreamWriter<V> extends InstanceWriter<V, OutputStream> {

    protected StreamWriter(V value, OutputStream destination) {
        super(value, destination);
    }

    protected abstract void writeStream(V value, OutputStream destination, long offset, long length) throws Exception;

    @Override
    protected final void writeEffectively(V value, OutputStream destination, long offset, long length) throws Exception {
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
        this.writeStream(value, destination, offset, length);
    }

    @Override
    public abstract StreamConfiguration<V> getConfiguration();

    public static class StreamConfiguration<V> extends WriterConfiguration<V, OutputStream> {
        private final BooleanOption FLUSH_STREAM = new BooleanOption(true);
        private final BooleanOption CLOSE_STREAM = new BooleanOption(true);

        public StreamConfiguration<V> setFlushStream(boolean state) {
            FLUSH_STREAM.set(state);
            return this;
        }

        public StreamConfiguration<V> flushStream() {
            return setFlushStream(true);
        }

        public StreamConfiguration<V> notFlushStream() {
            return setFlushStream(false);
        }

        public boolean shouldFlushStream() {
            return FLUSH_STREAM.get();
        }

        public StreamConfiguration<V> setCloseStream(boolean state) {
            CLOSE_STREAM.set(state);
            return this;
        }

        public StreamConfiguration<V> closeStream() {
            return setCloseStream(true);
        }

        public StreamConfiguration<V> notCloseStream() {
            return setCloseStream(false);
        }

        public boolean shouldCloseStream() {
            return CLOSE_STREAM.get();
        }
    }
}
