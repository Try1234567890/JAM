package com.github.trfiles.management.io.writers.toStream;

import com.github.trfiles.management.managers.FileManager;
import com.github.utilities.validators.Preconditions;

import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class PathStreamWriter extends StreamWriter<Path> {
    private PathStreamWriterConfiguration configuration;

    private PathStreamWriter(Path value, OutputStream destination, PathStreamWriterConfiguration configuration) {
        super(value, destination);
        this.configuration = configuration != null ? configuration : new PathStreamWriterConfiguration();
    }

    public static PathStreamWriter newInstance(Path value, OutputStream destination) {
        return new PathStreamWriter(value, destination, new PathStreamWriterConfiguration());
    }

    public static PathStreamWriter newInstance(Path value, OutputStream destination, PathStreamWriterConfiguration configuration) {
        return new PathStreamWriter(value, destination, configuration);
    }

    @Override
    protected void writeStream(Path value, OutputStream destination, long offset, long length) throws Exception {
        try (InputStream in = FileManager.newInputStream(value)) {
            StreamStreamWriter.newInstance(in, destination, getConfiguration().getStreamWriterConfig())
                    .write(offset, length);
        }
    }

    public PathStreamWriter withConfiguration(PathStreamWriterConfiguration configuration) {
        this.configuration = configuration;
        return this;
    }

    @Override
    public PathStreamWriterConfiguration getConfiguration() {
        return configuration;
    }

    @Override
    protected long _size(Path value) throws Exception {
        return Files.size(value);
    }

    public static class PathStreamWriterConfiguration extends StreamWriter.StreamConfiguration<Path> {
        private StreamStreamWriter.StreamStreamWriterConfiguration streamWriterConfig = new StreamStreamWriter.StreamStreamWriterConfiguration();

        public PathStreamWriterConfiguration withStringWriter(StreamStreamWriter.StreamStreamWriterConfiguration configuration) {
            this.streamWriterConfig = configuration;
            return this;
        }

        public StreamStreamWriter.StreamStreamWriterConfiguration getStreamWriterConfig() {
            return Preconditions.simpleNotNullOtherwise(streamWriterConfig, StreamStreamWriter.StreamStreamWriterConfiguration::new);
        }

    }
}
