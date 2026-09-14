package com.github.trfiles.management.io.writers.toStream;

import com.github.utilities.validators.Preconditions;

import java.io.OutputStream;
import java.util.Collection;

public class StringCollectionStreamWriter extends StreamWriter<Collection<String>> {
    private StringArrayStreamWriterConfiguration configuration;

    private StringCollectionStreamWriter(Collection<String> value, OutputStream destination, StringArrayStreamWriterConfiguration configuration) {
        super(value, destination);
        this.configuration = configuration != null ? configuration : new StringArrayStreamWriterConfiguration();
    }

    public static StringCollectionStreamWriter newInstance(Collection<String> value, OutputStream destination) {
        return new StringCollectionStreamWriter(value, destination, new StringArrayStreamWriterConfiguration());
    }

    public static StringCollectionStreamWriter newInstance(Collection<String> value, OutputStream destination, StringArrayStreamWriterConfiguration configuration) {
        return new StringCollectionStreamWriter(value, destination, configuration);
    }

    public StringCollectionStreamWriter withConfiguration(StringArrayStreamWriterConfiguration configuration) {
        this.configuration = configuration;
        return this;
    }

    @Override
    public StringArrayStreamWriterConfiguration getConfiguration() {
        return configuration;
    }


    @Override
    protected void writeStream(Collection<String> value, OutputStream destination, long offset, long length) throws Exception {
        String[] newValue = prepare(value, offset, length);
        if (newValue.length == 0) return;
        StringStreamWriter writer = StringStreamWriter.newInstance(newValue[0], destination, getConfiguration().getStringWriterConfig());

        for (String str : newValue) {
            writer.write(str, destination);
        }
    }

    private String[] prepare(Collection<String> value, long offset, long length) {
        if (value.isEmpty()) return new String[0];
        long size = length - offset;

        if (size > Integer.MAX_VALUE) {
            // sanity check. impossible java array size is limited to int max value.
            throw new IndexOutOfBoundsException("Cannot write " + size + " bytes to destination. Use streamings instead.");
        }

        String[] newValue = new String[(int) size];
        int index = 0;
        for (String line : value) {
            if (index < offset) {
                index++;
                continue;
            }
            if (index >= length) break;
            newValue[(int) (index - offset)] = line;
        }

        return newValue;
    }

    @Override
    protected long _size(Collection<String> value) throws Exception {
        return value.size();
    }

    public static class StringArrayStreamWriterConfiguration extends StreamConfiguration<Collection<String>> {
        private StringStreamWriter.StringStreamWriterConfiguration stringWriterConfig = new StringStreamWriter.StringStreamWriterConfiguration();

        public StringArrayStreamWriterConfiguration withStringWriter(StringStreamWriter.StringStreamWriterConfiguration configuration) {
            this.stringWriterConfig = configuration;
            return this;
        }

        public StringStreamWriter.StringStreamWriterConfiguration getStringWriterConfig() {
            return Preconditions.simpleNotNullOtherwise(stringWriterConfig, StringStreamWriter.StringStreamWriterConfiguration::new);
        }

    }
}
