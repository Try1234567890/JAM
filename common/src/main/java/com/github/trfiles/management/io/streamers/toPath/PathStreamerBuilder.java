package com.github.trfiles.management.io.streamers.toPath;

import com.github.utilities.validators.Preconditions;

import java.io.File;
import java.io.InputStream;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PathStreamerBuilder {
    private final Path path;

    public PathStreamerBuilder(Path path) {
        this.path = Preconditions.simpleParameterNotNull(path, "path");
    }

    public FromStreamToPath fromStream(InputStream is) {
        Preconditions.simpleParameterNotNull(is, "is");
        return FromStreamToPath.newInstance(is, path);
    }

    public KernelFromPathToPath fromPathViaKernel(Path path) {
        Preconditions.simpleParameterNotNull(path, "path");
        return KernelFromPathToPath.newInstance(path, this.path);
    }

    public KernelFromPathToPath fromPathViaKernel(File file) throws InvalidPathException {
        Preconditions.simpleParameterNotNull(file, "file");
        return fromPathViaKernel(file.toPath());
    }

    public KernelFromPathToPath fromPathViaKernel(String path) throws InvalidPathException {
        Preconditions.simpleParameterNotNull(path, "path");
        return fromPathViaKernel(Paths.get(path));
    }

    public FromPathToPath fromPath(Path path) {
        Preconditions.simpleParameterNotNull(path, "path");
        return FromPathToPath.newInstance(path, this.path);
    }

    public FromPathToPath fromPath(File file) throws InvalidPathException {
        Preconditions.simpleParameterNotNull(file, "file");
        return fromPath(file.toPath());
    }

    public FromPathToPath fromPath(String path) throws InvalidPathException {
        Preconditions.simpleParameterNotNull(path, "path");
        return fromPath(Paths.get(path));
    }
}
