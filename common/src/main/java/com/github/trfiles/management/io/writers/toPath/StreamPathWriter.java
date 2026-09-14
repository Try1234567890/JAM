package com.github.trfiles.management.io.writers.toPath;

import com.github.trfiles.management.io.writers.toStream.StreamStreamWriter;
import com.github.trfiles.management.io.writers.toStream.StreamWriter;
import com.github.utilities.validators.Preconditions;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Path;

public class StreamPathWriter extends PathWriter<InputStream> {
    private BytesPathWriterConfiguration configuration;

    private StreamPathWriter(InputStream value, Path destination, BytesPathWriterConfiguration configuration) {
        super(value, destination);
        this.configuration = configuration != null ? configuration : new BytesPathWriterConfiguration();
    }

    public static StreamPathWriter newInstance(InputStream value, Path destination) {
        return new StreamPathWriter(value, destination, new BytesPathWriterConfiguration());
    }

    public static StreamPathWriter newInstance(InputStream value, Path destination, BytesPathWriterConfiguration configuration) {
        return new StreamPathWriter(value, destination, configuration);
    }

    @Override
    protected StreamWriter<InputStream> toStreamWriter(InputStream value, OutputStream os) {
        return StreamStreamWriter.newInstance(value, os, getConfiguration().getStreamWriterConfig());
    }

    @Override
    protected long _size(InputStream value) throws IOException {
        return value.available();
    }

    public StreamPathWriter withConfiguration(BytesPathWriterConfiguration configuration) {
        this.configuration = configuration;
        return this;
    }



    @Override
    public BytesPathWriterConfiguration getConfiguration() {
        return configuration;
    }

    public static class BytesPathWriterConfiguration extends PathConfiguration<InputStream> {
        private StreamStreamWriter.StreamStreamWriterConfiguration streamWriterConfig = new StreamStreamWriter.StreamStreamWriterConfiguration();

        public BytesPathWriterConfiguration withStreamWriter(StreamStreamWriter.StreamStreamWriterConfiguration configuration) {
            this.streamWriterConfig = configuration;
            return this;
        }

        public StreamStreamWriter.StreamStreamWriterConfiguration getStreamWriterConfig() {
            return Preconditions.simpleNotNullOtherwise(streamWriterConfig, StreamStreamWriter.StreamStreamWriterConfiguration::new);
        }
    }
}
