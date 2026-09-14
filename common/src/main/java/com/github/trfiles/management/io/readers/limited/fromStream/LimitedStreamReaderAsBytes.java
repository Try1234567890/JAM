package com.github.trfiles.management.io.readers.limited.fromStream;

import java.io.IOException;
import java.io.InputStream;

public class LimitedStreamReaderAsBytes extends LimitedStreamReader<byte[]> {
    private StreamReaderAsBytesConfiguration configuration;

    private LimitedStreamReaderAsBytes(InputStream input, StreamReaderAsBytesConfiguration configuration) {
        super(input);
        this.configuration = configuration != null ? configuration : new StreamReaderAsBytesConfiguration();
    }

    public static LimitedStreamReaderAsBytes newInstance(InputStream input) {
        return new LimitedStreamReaderAsBytes(input, new StreamReaderAsBytesConfiguration());
    }

    public static LimitedStreamReaderAsBytes newInstance(InputStream input, StreamReaderAsBytesConfiguration configuration) {
        return new LimitedStreamReaderAsBytes(input, configuration);
    }

    public LimitedStreamReaderAsBytes withConfiguration(StreamReaderAsBytesConfiguration configuration) {
        this.configuration = configuration;
        return this;
    }


    @Override
    public StreamReaderAsBytesConfiguration getConfiguration() {
        return configuration;
    }

    @Override
    protected byte[] readEffectively(InputStream value, long offset, long length) throws Exception {
        long size = length - offset;

        if (size > Integer.MAX_VALUE) {
            throw new IndexOutOfBoundsException("Cannot read " + size + " bytes. Max array size is " + Integer.MAX_VALUE);
        }
        if (size < 0) {
            throw new IllegalArgumentException("Length must be greater than or equal to offset");
        }

        skipFully(value, offset);
        byte[] buffer = new byte[(int) size];
        int bytesRead = value.readNBytes(buffer, 0, (int) size);

        if (bytesRead != size) {
            throw new IOException("Cannot read " + size + " bytes from stream. Read only: " + bytesRead);
        }

        return buffer;
    }

    private void skipFully(InputStream stream, long toSkip) throws IOException {
        long remaining = toSkip;
        while (remaining > 0) {
            long skipped = stream.skip(remaining);
            if (skipped <= 0) {
                if (stream.read() == -1) {
                    throw new IOException("EOF reached while skipping. Couldn't skip " + toSkip + " bytes.");
                }
                skipped = 1;
            }
            remaining -= skipped;
        }
    }

    public static class StreamReaderAsBytesConfiguration extends StreamReaderConfiguration<byte[]> {

    }
}
