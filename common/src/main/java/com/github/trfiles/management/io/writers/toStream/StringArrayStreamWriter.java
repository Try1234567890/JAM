package com.github.trfiles.management.io.writers.toStream;

import com.github.utilities.validators.Preconditions;

import java.io.OutputStream;

public class StringArrayStreamWriter extends StreamWriter<String[]> {
    private StringCollectionStreamWriterConfiguration configuration;

    private StringArrayStreamWriter(String[] value, OutputStream destination, StringCollectionStreamWriterConfiguration configuration) {
        super(value, destination);
        this.configuration = configuration != null ? configuration : new StringCollectionStreamWriterConfiguration();
    }

    public static StringArrayStreamWriter newInstance(String[] value, OutputStream destination) {
        return new StringArrayStreamWriter(value, destination, new StringCollectionStreamWriterConfiguration());
    }

    public static StringArrayStreamWriter newInstance(String[] value, OutputStream destination, StringCollectionStreamWriterConfiguration configuration) {
        return new StringArrayStreamWriter(value, destination, configuration);
    }

    @Override
    protected void writeStream(String[] value, OutputStream destination, long offset, long length) throws Exception {
        // TODO: Abstract this with StringArrayStreamWriter.
        String[] newValue = prepare(value, offset, length);
        if (newValue.length == 0) return;
        StringStreamWriter writer = StringStreamWriter.newInstance(newValue[0], destination, getConfiguration().getStringWriterConfig());

        for (String str : newValue) {
            writer.write(str, destination);
        }

    }

    private String[] prepare(String[] value, long offset, long length) {
        if (value.length == 0) return value;
        long size = length - offset;

        if (size > Integer.MAX_VALUE) {
            // sanity check. impossible java array size is limited to int max value.
            throw new IndexOutOfBoundsException("Cannot write " + size + " bytes to destination. Use streamings instead.");
        }

        String[] newValue = new String[(int) size];
        System.arraycopy(value, (int) offset, newValue, 0, (int) size);
        return newValue;
    }

    public StringArrayStreamWriter withConfiguration(StringCollectionStreamWriterConfiguration configuration) {
        this.configuration = configuration;
        return this;
    }

    @Override
    public StringCollectionStreamWriterConfiguration getConfiguration() {
        return configuration;
    }

    @Override
    protected long _size(String[] value) throws Exception {
        return value.length;
    }

    public static class StringCollectionStreamWriterConfiguration extends StreamConfiguration<String[]> {
        private StringStreamWriter.StringStreamWriterConfiguration stringWriterConfig = new StringStreamWriter.StringStreamWriterConfiguration();

        public StringCollectionStreamWriterConfiguration withStringWriter(StringStreamWriter.StringStreamWriterConfiguration configuration) {
            this.stringWriterConfig = configuration;
            return this;
        }

        public StringStreamWriter.StringStreamWriterConfiguration getStringWriterConfig() {
            return Preconditions.simpleNotNullOtherwise(stringWriterConfig, StringStreamWriter.StringStreamWriterConfiguration::new);
        }

    }
}
