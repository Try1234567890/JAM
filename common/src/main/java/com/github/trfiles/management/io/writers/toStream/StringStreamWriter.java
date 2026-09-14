package com.github.trfiles.management.io.writers.toStream;

import com.github.utilities.validators.Preconditions;

import java.io.OutputStream;
import java.nio.charset.Charset;

public class StringStreamWriter extends StreamWriter<String> {
    private StringStreamWriterConfiguration configuration;

    private StringStreamWriter(String value, OutputStream destination, StringStreamWriterConfiguration configuration) {
        super(value, destination);
        this.configuration = configuration != null ? configuration : new StringStreamWriterConfiguration();
    }

    public static StringStreamWriter newInstance(String value, OutputStream destination) {
        return new StringStreamWriter(value, destination, new StringStreamWriterConfiguration());
    }

    public static StringStreamWriter newInstance(String value, OutputStream destination, StringStreamWriterConfiguration configuration) {
        return new StringStreamWriter(value, destination, configuration);
    }

    public StringStreamWriter withConfiguration(StringStreamWriterConfiguration configuration) {
        this.configuration = configuration;
        return this;
    }

    @Override
    public StringStreamWriterConfiguration getConfiguration() {
        return configuration;
    }

    @Override
    protected void writeStream(String value, OutputStream destination, long offset, long length) throws Exception {
        String newValue = prepare(value, offset, length);
        byte[] bytes = newValue.getBytes(getConfiguration().getCharset());
        destination.write(bytes);
    }

    private String prepare(String value, long offset, long length) throws IndexOutOfBoundsException {
        if (offset > Integer.MAX_VALUE) {
            throw new IndexOutOfBoundsException("The offset " + offset + " exceeds the maximum allowed string index (" + Integer.MAX_VALUE + ").");
        }

        if (offset < 0) {
            throw new IndexOutOfBoundsException("The offset cannot be negative: " + offset);
        }

        if (length > Integer.MAX_VALUE) {
            throw new IndexOutOfBoundsException("The end index/length " + length + " exceeds the maximum allowed string index (" + Integer.MAX_VALUE + ").");
        }

        return value.substring((int) offset, (int) length);
    }

    @Override
    protected long _size(String value) {
        return value.length();
    }

    public static class StringStreamWriterConfiguration extends StreamConfiguration<String> {
        private Charset charset = Charset.defaultCharset();

        public StringStreamWriterConfiguration withCharset(Charset charset) {
            this.charset = charset;
            return this;
        }

        public Charset getCharset() {
            return Preconditions.simpleNotNull(charset, Charset.defaultCharset());
        }

    }
}

















