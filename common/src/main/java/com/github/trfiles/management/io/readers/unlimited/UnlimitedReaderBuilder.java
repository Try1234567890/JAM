package com.github.trfiles.management.io.readers.unlimited;

import com.github.trfiles.management.io.readers.unlimited.fromPath.UnlimitedPathReaderBuilder;
import com.github.trfiles.management.io.readers.unlimited.fromStream.UnlimitedStreamReaderBuilder;
import com.github.utilities.validators.Preconditions;

import java.io.File;
import java.io.InputStream;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class UnlimitedReaderBuilder {
    private UnlimitedReaderBuilder() {}

    private static final class Holder {
        private static final UnlimitedReaderBuilder INSTANCE = new UnlimitedReaderBuilder();
    }

    public static UnlimitedReaderBuilder get() {
        return Holder.INSTANCE;
    }

    public UnlimitedStreamReaderBuilder fromStream(InputStream is) {
        Preconditions.simpleParameterNotNull(is, "is");
        return new UnlimitedStreamReaderBuilder(is);
    }

    public UnlimitedPathReaderBuilder fromPath(Path path) {
        Preconditions.simpleParameterNotNull(path, "path");
        return new UnlimitedPathReaderBuilder(path);
    }

    public UnlimitedPathReaderBuilder fromPath(File file) throws InvalidPathException {
        Preconditions.simpleParameterNotNull(file, "file");
        return fromPath(file.toPath());
    }

    public UnlimitedPathReaderBuilder fromPath(String path) throws InvalidPathException {
        Preconditions.simpleParameterNotNull(path, "path");
        return fromPath(Paths.get(path));
    }
}
