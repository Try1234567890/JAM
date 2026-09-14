package com.github.trfiles.management.io.readers.unlimited.fromPath;

import com.github.trfiles.management.io.readers.unlimited.fromPath.asArray.UnlimitedPathReaderAsStringArrayBuilder;
import com.github.trfiles.management.io.readers.unlimited.fromPath.asCollection.UnlimitedPathReaderAsStringCollectionBuilder;
import com.github.utilities.validators.Preconditions;

import java.nio.file.Path;

public class UnlimitedPathReaderBuilder {
    private final Path path;

    public UnlimitedPathReaderBuilder(Path path) {
        this.path = Preconditions.simpleParameterNotNull(path, "path");
    }

    public UnlimitedKernelPathReader viaKernel() {
        return UnlimitedKernelPathReader.newInstance(path);
    }

    public UnlimitedPathReaderAsString asString() {
        return UnlimitedPathReaderAsString.newInstance(path);
    }

    public UnlimitedPathReaderAsChars asChars() {
        return UnlimitedPathReaderAsChars.newInstance(path);
    }

    public UnlimitedPathReaderAsBytes asBytes() {
        return UnlimitedPathReaderAsBytes.newInstance(path);
    }

    public UnlimitedPathReaderAsStringCollectionBuilder asCollection() {
        return new UnlimitedPathReaderAsStringCollectionBuilder(path);
    }

    public UnlimitedPathReaderAsStringArrayBuilder asArray() {
        return new UnlimitedPathReaderAsStringArrayBuilder(path);
    }
}
