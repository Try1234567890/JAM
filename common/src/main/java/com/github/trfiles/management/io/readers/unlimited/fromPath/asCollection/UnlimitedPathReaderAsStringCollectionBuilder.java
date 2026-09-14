package com.github.trfiles.management.io.readers.unlimited.fromPath.asCollection;


import com.github.utilities.validators.Preconditions;

import java.nio.file.Path;

public class UnlimitedPathReaderAsStringCollectionBuilder {
    private final Path path;

    public UnlimitedPathReaderAsStringCollectionBuilder(Path path) {
        this.path = Preconditions.simpleParameterNotNull(path, "path");
    }

    public UnlimitedPathReaderAsStringCollection withFixedBounds() {
        return UnlimitedPathReaderAsStringCollectionWithFixedBounds.newInstance(path);
    }

    public UnlimitedPathReaderAsStringCollection withLinesBounds() {
        return UnlimitedPathReaderAsStringCollectionWithLinesBounds.newInstance(path);
    }
}
