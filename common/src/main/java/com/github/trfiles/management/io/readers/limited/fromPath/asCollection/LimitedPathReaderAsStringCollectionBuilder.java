package com.github.trfiles.management.io.readers.limited.fromPath.asCollection;


import com.github.trfiles.management.io.readers.unlimited.fromPath.asCollection.UnlimitedPathReaderAsStringCollection;
import com.github.trfiles.management.io.readers.unlimited.fromPath.asCollection.UnlimitedPathReaderAsStringCollectionWithFixedBounds;
import com.github.trfiles.management.io.readers.unlimited.fromPath.asCollection.UnlimitedPathReaderAsStringCollectionWithLinesBounds;
import com.github.utilities.validators.Preconditions;

import java.nio.file.Path;

public class LimitedPathReaderAsStringCollectionBuilder {
    private final Path path;

    public LimitedPathReaderAsStringCollectionBuilder(Path path) {
        this.path = Preconditions.simpleParameterNotNull(path, "path");
    }

    public LimitedPathReaderAsStringCollection withFixedBounds() {
        return LimitedPathReaderAsStringCollectionWithFixedBounds.newInstance(path);
    }

    public LimitedPathReaderAsStringCollection withLinesBounds() {
        return LimitedPathReaderAsStringCollectionWithLinesBounds.newInstance(path);
    }
}
