package com.github.trfiles.management.io.streamers.toStream;

import com.github.trfiles.management.managers.FileManager;
import com.github.utilities.validators.Preconditions;

import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Streams a {@link Path} into an {@link OutputStream}, reading the file content a chunk at a time
 * through a buffered {@link InputStream} opened on the source {@link Path}.
 */
public class FromPathToStream extends StreamStreamer<Path> {
    private FromPathToStreamConfiguration configuration;

    private FromPathToStream(Path source, OutputStream destination, FromPathToStreamConfiguration configuration) {
        super(source, destination);
        this.configuration = configuration != null ? configuration : new FromPathToStreamConfiguration();
    }

    public static FromPathToStream newInstance(Path source, OutputStream destination) {
        return new FromPathToStream(source, destination, new FromPathToStreamConfiguration());
    }

    public static FromPathToStream newInstance(Path source, OutputStream destination, FromPathToStreamConfiguration configuration) {
        return new FromPathToStream(source, destination, configuration);
    }

    @Override
    protected void streamToStream(Path source, OutputStream destination, long offset, long length) throws Exception {
        try (InputStream in = FileManager.newInputStream(source)) {
            FromStreamToStream.newInstance(in, destination, getConfiguration().getStreamerConfig())
                    .stream(offset, length);
        }
    }

    public FromPathToStream withConfiguration(FromPathToStreamConfiguration configuration) {
        this.configuration = configuration;
        return this;
    }

    @Override
    public FromPathToStreamConfiguration getConfiguration() {
        return configuration;
    }

    @Override
    protected long _size(Path value) throws Exception {
        return Files.size(value);
    }

    public static class FromPathToStreamConfiguration extends StreamConfiguration<Path> {
        private FromStreamToStream.FromStreamToStreamConfiguration streamerConfig = new FromStreamToStream.FromStreamToStreamConfiguration();

        public FromPathToStreamConfiguration withStreamer(FromStreamToStream.FromStreamToStreamConfiguration configuration) {
            this.streamerConfig = configuration;
            return this;
        }

        public FromStreamToStream.FromStreamToStreamConfiguration getStreamerConfig() {
            return Preconditions.simpleNotNullOtherwise(streamerConfig, FromStreamToStream.FromStreamToStreamConfiguration::new);
        }
    }
}
