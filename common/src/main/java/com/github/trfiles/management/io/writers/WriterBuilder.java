package com.github.trfiles.management.io.writers;

import com.github.trfiles.management.io.writers.toPath.PathWriterBuilder;
import com.github.trfiles.management.io.writers.toStream.StreamWriterBuilder;

import java.io.File;
import java.io.OutputStream;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class WriterBuilder {
    private WriterBuilder() {
    }

    private static final class Holder {
        private static final WriterBuilder INSTANCE = new WriterBuilder();
    }

    public static WriterBuilder get() {
        return Holder.INSTANCE;
    }

    public StreamWriterBuilder toStream(OutputStream os) {
        return new StreamWriterBuilder(os);
    }

    public PathWriterBuilder toPath(Path path) {
        return new PathWriterBuilder(path);
    }

    public PathWriterBuilder toPath(File file) throws InvalidPathException {
        return toPath(file.toPath());
    }

    public PathWriterBuilder toPath(String path) throws InvalidPathException {
        return toPath(Paths.get(path));
    }
}
