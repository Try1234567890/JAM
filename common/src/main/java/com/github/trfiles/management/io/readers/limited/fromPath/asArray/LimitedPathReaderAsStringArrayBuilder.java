package com.github.trfiles.management.io.readers.limited.fromPath.asArray;

import com.github.trfiles.management.io.readers.unlimited.fromPath.asArray.UnlimitedPathReaderAsStringArray;
import com.github.trfiles.management.io.readers.unlimited.fromPath.asArray.UnlimitedPathReaderAsStringArrayWithFixedBounds;
import com.github.trfiles.management.io.readers.unlimited.fromPath.asArray.UnlimitedPathReaderAsStringArrayWithLinesBounds;
import com.github.utilities.validators.Preconditions;

import java.nio.file.Path;

public class LimitedPathReaderAsStringArrayBuilder {
    private final Path path;

    public LimitedPathReaderAsStringArrayBuilder(Path path) {
        this.path = Preconditions.simpleParameterNotNull(path, "path");
    }

    public LimitedPathReaderAsStringArray withFixedBounds() {
        return LimitedPathReaderAsStringArrayWithFixedBounds.newInstance(path);
    }

    public LimitedPathReaderAsStringArray withLinesBounds() {
        return LimitedPathReaderAsStringArrayWithLinesBounds.newInstance(path);
    }
}
