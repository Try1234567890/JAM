package com.github.trfiles.management.io.readers.limited.fromPath;

import com.github.trfiles.management.io.readers.limited.fromPath.asArray.LimitedPathReaderAsStringArrayBuilder;
import com.github.trfiles.management.io.readers.limited.fromPath.asCollection.LimitedPathReaderAsStringCollectionBuilder;
import com.github.trfiles.management.io.readers.unlimited.fromPath.UnlimitedKernelPathReader;
import com.github.trfiles.management.io.readers.unlimited.fromPath.UnlimitedPathReaderAsBytes;
import com.github.trfiles.management.io.readers.unlimited.fromPath.UnlimitedPathReaderAsChars;
import com.github.trfiles.management.io.readers.unlimited.fromPath.UnlimitedPathReaderAsString;
import com.github.trfiles.management.io.readers.unlimited.fromPath.asArray.UnlimitedPathReaderAsStringArrayBuilder;
import com.github.trfiles.management.io.readers.unlimited.fromPath.asCollection.UnlimitedPathReaderAsStringCollectionBuilder;
import com.github.utilities.validators.Preconditions;

import java.nio.file.Path;

public class LimitedPathReaderBuilder {
    private final Path path;

    public LimitedPathReaderBuilder(Path path) {
        this.path = Preconditions.simpleParameterNotNull(path, "path");
    }

    public LimitedKernelPathReader viaKernel() {
        return LimitedKernelPathReader.newInstance(path);
    }

    public LimitedPathReaderAsString asString() {
        return LimitedPathReaderAsString.newInstance(path);
    }

    public LimitedPathReaderAsChars asChars() {
        return LimitedPathReaderAsChars.newInstance(path);
    }

    public LimitedPathReaderAsBytes asBytes() {
        return LimitedPathReaderAsBytes.newInstance(path);
    }

    public LimitedPathReaderAsStringCollectionBuilder asCollection() {
        return new LimitedPathReaderAsStringCollectionBuilder(path);
    }

    public LimitedPathReaderAsStringArrayBuilder asArray() {
        return new LimitedPathReaderAsStringArrayBuilder(path);
    }
}
