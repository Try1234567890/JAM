package com.github.trfiles.management.io.streamers.toPath;

import com.github.trfiles.management.io.streamers.toStream.FromPathToStream;
import com.github.trfiles.management.io.streamers.toStream.StreamStreamer;
import com.github.utilities.validators.Preconditions;

import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Streams a {@link Path} into another {@link Path}, going through a buffered {@link java.io.InputStream}/
 * {@link OutputStream} pair. This is the "portable" Path-to-Path implementation: it works regardless of
 * the underlying filesystem and supports the full pre/post/success/fail processing hooks a chunk at a time.
 * <p>
 * When copying two regular, local files and the extra portability/hooks are not needed, prefer
 * {@link KernelFromPathToPath}, which delegates the transfer to the operating system for better performance.
 */
public class FromPathToPath extends PathStreamer<Path> {
    private FromPathToPathConfiguration configuration;

    private FromPathToPath(Path source, Path destination, FromPathToPathConfiguration configuration) {
        super(source, destination);
        this.configuration = configuration != null ? configuration : new FromPathToPathConfiguration();
    }

    public static FromPathToPath newInstance(Path source, Path destination) {
        return new FromPathToPath(source, destination, new FromPathToPathConfiguration());
    }

    public static FromPathToPath newInstance(Path source, Path destination, FromPathToPathConfiguration configuration) {
        return new FromPathToPath(source, destination, configuration);
    }

    @Override
    protected StreamStreamer<Path> toStreamStreamer(Path source, OutputStream os) {
        return FromPathToStream.newInstance(source, os, getConfiguration().getStreamerConfig());
    }

    @Override
    public FromPathToPathConfiguration getConfiguration() {
        return configuration;
    }

    public FromPathToPath withConfiguration(FromPathToPathConfiguration configuration) {
        this.configuration = configuration;
        return this;
    }

    @Override
    protected long _size(Path value) throws Exception {
        return Files.size(value);
    }

    public static class FromPathToPathConfiguration extends PathConfiguration<Path> {
        private FromPathToStream.FromPathToStreamConfiguration streamerConfig = new FromPathToStream.FromPathToStreamConfiguration();

        public FromPathToPathConfiguration withStreamer(FromPathToStream.FromPathToStreamConfiguration configuration) {
            this.streamerConfig = configuration;
            return this;
        }

        public FromPathToStream.FromPathToStreamConfiguration getStreamerConfig() {
            return Preconditions.simpleNotNullOtherwise(streamerConfig, FromPathToStream.FromPathToStreamConfiguration::new);
        }
    }
}
