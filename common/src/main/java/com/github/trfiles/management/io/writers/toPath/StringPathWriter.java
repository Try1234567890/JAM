package com.github.trfiles.management.io.writers.toPath;

import com.github.trfiles.management.io.writers.toStream.StreamWriter;
import com.github.trfiles.management.io.writers.toStream.StringStreamWriter;
import com.github.utilities.validators.Preconditions;

import java.io.OutputStream;
import java.nio.file.Path;

public class StringPathWriter extends PathWriter<String> {
    private StringPathWriterConfiguration configuration;

    private StringPathWriter(String value, Path destination, StringPathWriterConfiguration configuration) {
        super(value, destination);
        this.configuration = configuration != null ? configuration : new StringPathWriterConfiguration();
    }

    public static StringPathWriter newInstance(String value, Path destination) {
        return new StringPathWriter(value, destination, new StringPathWriterConfiguration());
    }

    public static StringPathWriter newInstance(String value, Path destination, StringPathWriterConfiguration configuration) {
        return new StringPathWriter(value, destination, configuration);
    }

    @Override
    protected StreamWriter<String> toStreamWriter(String value, OutputStream os) {
        return StringStreamWriter.newInstance(value, os, getConfiguration().getStreamWriterConfig());
    }

    @Override
    protected long _size(String value) {
        return value.length();
    }

    public StringPathWriter withConfiguration(StringPathWriterConfiguration configuration) {
        this.configuration = configuration;
        return this;
    }



    @Override
    public StringPathWriterConfiguration getConfiguration() {
        return configuration;
    }

    public static class StringPathWriterConfiguration extends PathConfiguration<String> {
        private StringStreamWriter.StringStreamWriterConfiguration streamWriterConfig = new StringStreamWriter.StringStreamWriterConfiguration();

        public StringPathWriterConfiguration withStreamWriter(StringStreamWriter.StringStreamWriterConfiguration configuration) {
            this.streamWriterConfig = configuration;
            return this;
        }

        public StringStreamWriter.StringStreamWriterConfiguration getStreamWriterConfig() {
            return Preconditions.simpleNotNullOtherwise(streamWriterConfig, StringStreamWriter.StringStreamWriterConfiguration::new);
        }

    }
}
