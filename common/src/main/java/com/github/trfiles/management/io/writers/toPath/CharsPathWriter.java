package com.github.trfiles.management.io.writers.toPath;

import com.github.trfiles.management.io.writers.toStream.CharsStreamWriter;
import com.github.trfiles.management.io.writers.toStream.StreamWriter;
import com.github.utilities.validators.Preconditions;

import java.io.OutputStream;
import java.nio.file.Path;

public class CharsPathWriter extends PathWriter<char[]> {
    private BytesPathWriterConfiguration configuration;

    private CharsPathWriter(char[] value, Path destination, BytesPathWriterConfiguration configuration) {
        super(value, destination);
        this.configuration = configuration != null ? configuration : new BytesPathWriterConfiguration();
    }

    public static CharsPathWriter newInstance(char[] value, Path destination) {
        return new CharsPathWriter(value, destination, new BytesPathWriterConfiguration());
    }

    public static CharsPathWriter newInstance(char[] value, Path destination, BytesPathWriterConfiguration configuration) {
        return new CharsPathWriter(value, destination, configuration);
    }

    @Override
    protected StreamWriter<char[]> toStreamWriter(char[] value, OutputStream os) {
        return CharsStreamWriter.newInstance(value, os, getConfiguration().getStreamWriterConfig());
    }

    @Override
    protected long _size(char[] value) {
        return value.length;
    }

    public CharsPathWriter withConfiguration(BytesPathWriterConfiguration configuration) {
        this.configuration = configuration;
        return this;
    }



    @Override
    public BytesPathWriterConfiguration getConfiguration() {
        return configuration;
    }

    public static class BytesPathWriterConfiguration extends PathConfiguration<char[]> {
        private CharsStreamWriter.BytesStreamWriterConfiguration streamWriterConfig = new CharsStreamWriter.BytesStreamWriterConfiguration();

        public BytesPathWriterConfiguration withStreamWriter(CharsStreamWriter.BytesStreamWriterConfiguration configuration) {
            this.streamWriterConfig = configuration;
            return this;
        }

        public CharsStreamWriter.BytesStreamWriterConfiguration getStreamWriterConfig() {
            return Preconditions.simpleNotNullOtherwise(streamWriterConfig, CharsStreamWriter.BytesStreamWriterConfiguration::new);
        }
    }
}
