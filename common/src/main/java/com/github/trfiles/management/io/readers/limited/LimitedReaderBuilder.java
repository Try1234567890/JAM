package com.github.trfiles.management.io.readers.limited;

import com.github.trfiles.management.io.readers.limited.fromPath.LimitedPathReaderBuilder;
import com.github.trfiles.management.io.readers.limited.fromStream.LimitedStreamReaderBuilder;
import com.github.utilities.validators.Preconditions;

import java.io.File;
import java.io.InputStream;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class LimitedReaderBuilder {
    private LimitedReaderBuilder() {}

    private static final class Holder {
        private static final LimitedReaderBuilder INSTANCE = new LimitedReaderBuilder();
    }

    public static LimitedReaderBuilder get() {
        return Holder.INSTANCE;
    }

    public LimitedStreamReaderBuilder fromStream(InputStream is) {
        Preconditions.simpleParameterNotNull(is, "is");
        return new LimitedStreamReaderBuilder(is);
    }

    public LimitedPathReaderBuilder fromPath(Path path) {
        Preconditions.simpleParameterNotNull(path, "path");
        return new LimitedPathReaderBuilder(path);
    }

    public LimitedPathReaderBuilder fromPath(File file) throws InvalidPathException {
        Preconditions.simpleParameterNotNull(file, "file");
        return fromPath(file.toPath());
    }

    public LimitedPathReaderBuilder fromPath(String path) throws InvalidPathException {
        Preconditions.simpleParameterNotNull(path, "path");
        return fromPath(Paths.get(path));
    }
}
