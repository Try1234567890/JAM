package com.github.trfiles.management.io.streamers.toStream;

import com.github.utilities.validators.Preconditions;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class StreamStreamerBuilder {
    private final OutputStream os;

    public StreamStreamerBuilder(OutputStream os) {
        this.os = Preconditions.simpleParameterNotNull(os, "os");
    }

    public FromStreamToStream fromStream(InputStream is) {
        Preconditions.simpleParameterNotNull(is, "is");
        return FromStreamToStream.newInstance(is, os);
    }

    public FromPathToStream fromPath(Path path) {
        Preconditions.simpleParameterNotNull(path, "path");
        return FromPathToStream.newInstance(path, os);
    }

    public FromPathToStream fromPath(File file) throws InvalidPathException {
        Preconditions.simpleParameterNotNull(file, "file");
        return fromPath(file.toPath());
    }

    public FromPathToStream fromPath(String path) throws InvalidPathException {
        Preconditions.simpleParameterNotNull(path, "path");
        return fromPath(Paths.get(path));
    }
}
