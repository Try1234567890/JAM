package com.github.trfiles.management.io.readers.unlimited.fromStream.asArray;

import com.github.trfiles.management.io.readers.unlimited.fromPath.asArray.UnlimitedPathReaderAsStringArrayWithFixedBounds;
import com.github.utilities.validators.Preconditions;

import java.io.InputStream;

public class UnlimitedStreamReaderAsStringArrayBuilder {
    private final InputStream is;

    public UnlimitedStreamReaderAsStringArrayBuilder(InputStream is) {
        this.is = Preconditions.simpleParameterNotNull(is, "is");
    }

    public UnlimitedStreamReaderAsStringArray withFixedBounds() {
        return UnlimitedStreamReaderAsStringArrayWithFixedBounds.newInstance(is);
    }

    public UnlimitedStreamReaderAsStringArray withLinesBounds() {
        return UnlimitedStreamReaderAsStringArrayWithLinesBounds.newInstance(is);
    }
}
