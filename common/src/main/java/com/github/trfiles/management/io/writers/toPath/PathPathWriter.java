package com.github.trfiles.management.io.writers.toPath;

import com.github.trfiles.management.io.writers.toStream.PathStreamWriter;
import com.github.trfiles.management.io.writers.toStream.StreamWriter;
import com.github.utilities.validators.Preconditions;

import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class PathPathWriter extends PathWriter<Path> {
    private PathPathWriterConfiguration configuration;

    private PathPathWriter(Path value, Path destination, PathPathWriterConfiguration configuration) {
        super(value, destination);
        this.configuration = configuration != null ? configuration : new PathPathWriterConfiguration();
    }

    public static PathPathWriter newInstance(Path value, Path destination) {
        return new PathPathWriter(value, destination, new PathPathWriterConfiguration());
    }

    public static PathPathWriter newInstance(Path value, Path destination, PathPathWriterConfiguration configuration) {
        return new PathPathWriter(value, destination, configuration);
    }

    @Override
    protected StreamWriter<Path> toStreamWriter(Path value, OutputStream os) {
        return PathStreamWriter.newInstance(value, os, getConfiguration().getStreamWriterConfig());
    }

    @Override
    public PathPathWriterConfiguration getConfiguration() {
        return configuration;
    }

    public PathPathWriter withConfiguration(PathPathWriterConfiguration configuration) {
        this.configuration = configuration;
        return this;
    }

    @Override
    protected long _size(Path value) throws Exception {
        return Files.size(value);
    }

    public static class PathPathWriterConfiguration extends PathConfiguration<Path> {
        private PathStreamWriter.PathStreamWriterConfiguration streamWriterConfig = new PathStreamWriter.PathStreamWriterConfiguration();

        public PathPathWriterConfiguration withStreamWriter(PathStreamWriter.PathStreamWriterConfiguration configuration) {
            this.streamWriterConfig = configuration;
            return this;
        }

        public PathStreamWriter.PathStreamWriterConfiguration getStreamWriterConfig() {
            return Preconditions.simpleNotNullOtherwise(streamWriterConfig, PathStreamWriter.PathStreamWriterConfiguration::new);
        }
    }
}
