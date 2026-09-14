package com.github.trfiles.management.io.streamers.toPath;

import com.github.trfiles.management.io.streamers.toStream.FromStreamToStream;
import com.github.trfiles.management.io.streamers.toStream.StreamStreamer;
import com.github.utilities.validators.Preconditions;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Path;

/**
 * Streams an {@link InputStream} into a {@link Path}, opening the destination file as an
 * {@link OutputStream} and delegating the actual chunk-by-chunk copy to {@link FromStreamToStream}.
 */
public class FromStreamToPath extends PathStreamer<InputStream> {
    private FromStreamToPathConfiguration configuration;

    private FromStreamToPath(InputStream source, Path destination, FromStreamToPathConfiguration configuration) {
        super(source, destination);
        this.configuration = configuration != null ? configuration : new FromStreamToPathConfiguration();
    }

    public static FromStreamToPath newInstance(InputStream source, Path destination) {
        return new FromStreamToPath(source, destination, new FromStreamToPathConfiguration());
    }

    public static FromStreamToPath newInstance(InputStream source, Path destination, FromStreamToPathConfiguration configuration) {
        return new FromStreamToPath(source, destination, configuration);
    }

    @Override
    protected StreamStreamer<InputStream> toStreamStreamer(InputStream source, OutputStream os) {
        return FromStreamToStream.newInstance(source, os, getConfiguration().getStreamerConfig());
    }

    @Override
    protected long _size(InputStream value) throws IOException {
        return value.available();
    }

    public FromStreamToPath withConfiguration(FromStreamToPathConfiguration configuration) {
        this.configuration = configuration;
        return this;
    }

    @Override
    public FromStreamToPathConfiguration getConfiguration() {
        return configuration;
    }

    public static class FromStreamToPathConfiguration extends PathConfiguration<InputStream> {
        private FromStreamToStream.FromStreamToStreamConfiguration streamerConfig = new FromStreamToStream.FromStreamToStreamConfiguration();

        public FromStreamToPathConfiguration withStreamer(FromStreamToStream.FromStreamToStreamConfiguration configuration) {
            this.streamerConfig = configuration;
            return this;
        }

        public FromStreamToStream.FromStreamToStreamConfiguration getStreamerConfig() {
            return Preconditions.simpleNotNullOtherwise(streamerConfig, FromStreamToStream.FromStreamToStreamConfiguration::new);
        }
    }
}
