package com.github.trfiles.management.io.writers.toStream;

import java.io.OutputStream;

public class BytesStreamWriter extends StreamWriter<byte[]> {
    private BytesStreamWriterConfiguration configuration;

    private BytesStreamWriter(byte[] value, OutputStream destination, BytesStreamWriterConfiguration configuration) {
        super(value, destination);
        this.configuration = configuration != null ? configuration : new BytesStreamWriterConfiguration();
    }

    public static BytesStreamWriter newInstance(byte[] value, OutputStream destination) {
        return new BytesStreamWriter(value, destination, new BytesStreamWriterConfiguration());
    }

    public static BytesStreamWriter newInstance(byte[] value, OutputStream destination, BytesStreamWriterConfiguration configuration) {
        return new BytesStreamWriter(value, destination, configuration);
    }

    @Override
    protected void writeStream(byte[] value, OutputStream destination, long offset, long length) throws Exception {
        byte[] newValue = prepare(value, offset, length);
        destination.write(newValue);
    }

    private byte[] prepare(byte[] value, long offset, long length) throws IndexOutOfBoundsException {
        long size = length - offset;

        if (size > Integer.MAX_VALUE) {
            // sanity check. impossible java array size is limited to int max value.
            throw new IndexOutOfBoundsException("Cannot write " + size + " bytes to destination. Use streamings instead.");
        }

        byte[] newValue = new byte[(int) size];
        System.arraycopy(value, (int) offset, newValue, 0, (int) size);
        return newValue;
    }

    @Override
    protected long _size(byte[] value) throws Exception {
        return value.length;
    }

    public BytesStreamWriter withConfiguration(BytesStreamWriterConfiguration configuration) {
        this.configuration = configuration;
        return this;
    }



    @Override
    public BytesStreamWriterConfiguration getConfiguration() {
        return configuration;
    }

    public static class BytesStreamWriterConfiguration extends StreamWriter.StreamConfiguration<byte[]> {

    }
}
