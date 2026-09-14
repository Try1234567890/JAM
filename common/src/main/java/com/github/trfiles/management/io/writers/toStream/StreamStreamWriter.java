package com.github.trfiles.management.io.writers.toStream;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class StreamStreamWriter extends StreamWriter<InputStream> {
    private StreamStreamWriterConfiguration configuration;

    private StreamStreamWriter(InputStream value, OutputStream destination, StreamStreamWriterConfiguration configuration) {
        super(value, destination);
        this.configuration = configuration != null ? configuration : new StreamStreamWriterConfiguration();
    }

    public static StreamStreamWriter newInstance(InputStream value, OutputStream destination) {
        return new StreamStreamWriter(value, destination, new StreamStreamWriterConfiguration());
    }

    public static StreamStreamWriter newInstance(InputStream value, OutputStream destination, StreamStreamWriterConfiguration configuration) {
        return new StreamStreamWriter(value, destination, configuration);
    }

    @Override
    protected void writeStream(InputStream value, OutputStream destination, long offset, long length) throws Exception {
        BufferedInputStream bis = new BufferedInputStream(value);
        long skipped = bis.skip(offset - 1); // remove 1 due to offset inclusivity

        if (skipped != offset) {
            throw new IOException("Cannot skip " + offset + " bytes of the stream. Bytes effectively skipped: " + skipped);
        }

        long size = length - offset;
        int curr;
        int index = 0;

        while ((curr = bis.read()) != -1) {
            // ++ before to ensure length exclusivity.
            if (++index == size) break;
            destination.write(curr);
        }
    }

    @Override
    protected long _size(InputStream value) throws IOException {
        return value.available();
    }

    public StreamStreamWriter withConfiguration(StreamStreamWriterConfiguration configuration) {
        this.configuration = configuration;
        return this;
    }

    @Override
    public StreamStreamWriterConfiguration getConfiguration() {
        return configuration;
    }

    public static class StreamStreamWriterConfiguration extends StreamConfiguration<InputStream> {

    }
}
