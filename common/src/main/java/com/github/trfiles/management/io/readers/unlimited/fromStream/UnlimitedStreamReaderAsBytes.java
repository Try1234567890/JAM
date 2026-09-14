package com.github.trfiles.management.io.readers.unlimited.fromStream;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.LinkedList;

public class UnlimitedStreamReaderAsBytes extends UnlimitedStreamReader<Collection<byte[]>> {
    private StreamReaderAsBytesConfiguration configuration;

    private UnlimitedStreamReaderAsBytes(InputStream input, StreamReaderAsBytesConfiguration configuration) {
        super(input);
        this.configuration = configuration != null ? configuration : new StreamReaderAsBytesConfiguration();
    }

    public static UnlimitedStreamReaderAsBytes newInstance(InputStream input) {
        return new UnlimitedStreamReaderAsBytes(input, new StreamReaderAsBytesConfiguration());
    }

    public static UnlimitedStreamReaderAsBytes newInstance(InputStream input, StreamReaderAsBytesConfiguration configuration) {
        return new UnlimitedStreamReaderAsBytes(input, configuration);
    }

    public UnlimitedStreamReaderAsBytes withConfiguration(StreamReaderAsBytesConfiguration configuration) {
        this.configuration = configuration;
        return this;
    }


    @Override
    public StreamReaderAsBytesConfiguration getConfiguration() {
        return configuration;
    }

    @Override
    protected Collection<byte[]> readEffectively(InputStream value, long offset, long length) throws Exception {
        long size = length - offset;
        if (size < 0) throw new IllegalArgumentException("Length must be greater than or equal to offset");
        skipFully(value, offset);

        long fullBytesAmt = Math.floorDiv(size, Integer.MAX_VALUE);
        int remBytes = (int) (size % Integer.MAX_VALUE);
        Collection<byte[]> allBytes = new LinkedList<>();

        for (double i = 0; i < fullBytesAmt; i++) {
            byte[] bytes = read(Integer.MAX_VALUE, value);
            allBytes.add(bytes);
        }

        if (remBytes != 0) {
            byte[] bytes = read(remBytes, value);
            allBytes.add(bytes);
        }

        return allBytes;
    }

    private byte[] read(int size, InputStream value) throws IOException {
        byte[] buffer = new byte[size];
        int bytesRead = value.readNBytes(buffer, 0, size);

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

    public static class StreamReaderAsBytesConfiguration extends StreamReaderConfiguration<Collection<byte[]>> {

    }
}
