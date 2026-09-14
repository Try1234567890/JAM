package com.github.trfiles.management.io.writers.toPath;

import com.github.trfiles.management.io.writers.toStream.BytesStreamWriter;
import com.github.trfiles.management.io.writers.toStream.StreamWriter;
import com.github.utilities.validators.Preconditions;

import java.io.OutputStream;
import java.nio.file.Path;

public class BytesPathWriter extends PathWriter<byte[]> {
    private BytesPathWriterConfiguration configuration;

    private BytesPathWriter(byte[] value, Path destination, BytesPathWriterConfiguration configuration) {
        super(value, destination);
        this.configuration = configuration != null ? configuration : new BytesPathWriterConfiguration();
    }

    public static BytesPathWriter newInstance(byte[] value, Path destination) {
        return new BytesPathWriter(value, destination, new BytesPathWriterConfiguration());
    }

    public static BytesPathWriter newInstance(byte[] value, Path destination, BytesPathWriterConfiguration configuration) {
        return new BytesPathWriter(value, destination, configuration);
    }

    @Override
    protected StreamWriter<byte[]> toStreamWriter(byte[] value, OutputStream os) {
        return BytesStreamWriter.newInstance(value, os, getConfiguration().getStreamWriterConfig());
    }

    @Override
    protected long _size(byte[] value) throws Exception {
        return value.length;
    }

    public BytesPathWriter withConfiguration(BytesPathWriterConfiguration configuration) {
        this.configuration = configuration;
        return this;
    }



    @Override
    public BytesPathWriterConfiguration getConfiguration() {
        return configuration;
    }

    public static class BytesPathWriterConfiguration extends PathConfiguration<byte[]> {
        private BytesStreamWriter.BytesStreamWriterConfiguration streamWriterConfig = new BytesStreamWriter.BytesStreamWriterConfiguration();

        public BytesPathWriterConfiguration withStreamWriter(BytesStreamWriter.BytesStreamWriterConfiguration configuration) {
            this.streamWriterConfig = configuration;
            return this;
        }

        public BytesStreamWriter.BytesStreamWriterConfiguration getStreamWriterConfig() {
            return Preconditions.simpleNotNullOtherwise(streamWriterConfig, BytesStreamWriter.BytesStreamWriterConfiguration::new);
        }
    }
}
