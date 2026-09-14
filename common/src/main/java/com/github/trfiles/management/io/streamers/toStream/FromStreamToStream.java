package com.github.trfiles.management.io.streamers.toStream;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Streams an {@link InputStream} into an {@link OutputStream}, reading it a chunk (of
 * {@link com.github.trfiles.management.io.streamers.StreamerConfiguration#getBufferSize() bufferSize} bytes)
 * at a time and writing each chunk immediately, instead of loading the whole source into memory.
 * <p>
 * This is the "leaf" implementation of the {@code streamers} package: every other {@code Streamer}
 * that has to move bytes between two endpoints eventually delegates down to an instance of this class.
 * <p>
 * Because the source is consumed strictly sequentially and only one chunk is ever kept in memory,
 * this class can stream sources of any size - including a genuinely unbounded one, when used with
 * a {@code length} of {@link com.github.trfiles.management.io.streamers.Streamer#UNBOUNDED}.
 */
public class FromStreamToStream extends StreamStreamer<InputStream> {
    private FromStreamToStreamConfiguration configuration;

    private FromStreamToStream(InputStream source, OutputStream destination, FromStreamToStreamConfiguration configuration) {
        super(source, destination);
        this.configuration = configuration != null ? configuration : new FromStreamToStreamConfiguration();
    }

    public static FromStreamToStream newInstance(InputStream source, OutputStream destination) {
        return new FromStreamToStream(source, destination, new FromStreamToStreamConfiguration());
    }

    public static FromStreamToStream newInstance(InputStream source, OutputStream destination, FromStreamToStreamConfiguration configuration) {
        return new FromStreamToStream(source, destination, configuration);
    }

    @Override
    protected void streamToStream(InputStream source, OutputStream destination, long offset, long length) throws Exception {
        InputStream in = source instanceof BufferedInputStream
                ? source
                : new BufferedInputStream(source, getConfiguration().getBufferSize());

        long toSkip = offset;
        while (toSkip > 0) {
            long skipped = in.skip(toSkip);
            if (skipped <= 0) {
                throw new IOException("Cannot skip " + offset + " bytes of the source: the stream ended prematurely.");
            }
            toSkip -= skipped;
        }

        byte[] buffer = new byte[getConfiguration().getBufferSize()];
        // "length" is an exclusive end index (consistent with Writer's semantics), not a byte count:
        // the actual amount of bytes to copy is "length - offset". UNBOUNDED (-1) keeps the loop going
        // until the source is exhausted.
        long remaining = length == UNBOUNDED ? UNBOUNDED : length - offset;
        int read;

        while (remaining == UNBOUNDED || remaining > 0) {
            int toRead = remaining == UNBOUNDED ? buffer.length : (int) Math.min(buffer.length, remaining);
            if ((read = in.read(buffer, 0, toRead)) == -1) {
                break;
            }

            destination.write(buffer, 0, read);
            if (remaining != UNBOUNDED) {
                remaining -= read;
            }
        }
    }

    @Override
    protected long _size(InputStream value) throws IOException {
        return value.available();
    }

    public FromStreamToStream withConfiguration(FromStreamToStreamConfiguration configuration) {
        this.configuration = configuration;
        return this;
    }

    @Override
    public FromStreamToStreamConfiguration getConfiguration() {
        return configuration;
    }

    public static class FromStreamToStreamConfiguration extends StreamConfiguration<InputStream> {

    }
}
