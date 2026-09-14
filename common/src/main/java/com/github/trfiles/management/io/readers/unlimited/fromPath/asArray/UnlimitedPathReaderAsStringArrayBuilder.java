package com.github.trfiles.management.io.readers.unlimited.fromPath.asArray;

import com.github.utilities.validators.Preconditions;

import java.nio.file.Path;

public class UnlimitedPathReaderAsStringArrayBuilder {
    private final Path path;

    public UnlimitedPathReaderAsStringArrayBuilder(Path path) {
        this.path = Preconditions.simpleParameterNotNull(path, "path");
    }

    public UnlimitedPathReaderAsStringArray withFixedBounds() {
        return UnlimitedPathReaderAsStringArrayWithFixedBounds.newInstance(path);
    }

    public UnlimitedPathReaderAsStringArray withLinesBounds() {
        return UnlimitedPathReaderAsStringArrayWithLinesBounds.newInstance(path);
    }
}
