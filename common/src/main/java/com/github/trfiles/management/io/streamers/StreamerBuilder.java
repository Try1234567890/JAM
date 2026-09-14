package com.github.trfiles.management.io.streamers;

import com.github.trfiles.management.io.streamers.toPath.PathStreamerBuilder;
import com.github.trfiles.management.io.streamers.toStream.StreamStreamerBuilder;
import com.github.utilities.validators.Preconditions;

import java.io.File;
import java.io.OutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;

public class StreamerBuilder {
    private StreamerBuilder() {
    }

    private static final class Holder {
        private static final StreamerBuilder INSTANCE = new StreamerBuilder();
    }

    public static StreamerBuilder get() {
        return Holder.INSTANCE;
    }

    public StreamStreamerBuilder toStream(OutputStream os) {
        Preconditions.simpleParameterNotNull(os, "os");
        return new StreamStreamerBuilder(os);
    }

    public PathStreamerBuilder toPath(Path path) {
        Preconditions.simpleParameterNotNull(path, "path");
        return new PathStreamerBuilder(path.toAbsolutePath());
    }

    public PathStreamerBuilder toPath(File file) {
        Preconditions.simpleParameterNotNull(file, "file");
        return toPath(file.toPath());
    }

    public PathStreamerBuilder toPath(String path) {
        Preconditions.simpleParameterNotNull(path, "path");
        return toPath(Paths.get(path));
    }
}
