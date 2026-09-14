package com.github.trfiles.management.io.readers.unlimited.fromStream.asCollection;


import com.github.utilities.validators.Preconditions;

import java.io.InputStream;

public class UnlimitedStreamReaderAsStringCollectionBuilder {
    private final InputStream is;

    public UnlimitedStreamReaderAsStringCollectionBuilder(InputStream is) {
        this.is = Preconditions.simpleParameterNotNull(is, "is");
    }

    public UnlimitedStreamReaderAsStringCollectionWithFixedBounds withFixedBounds() {
        return UnlimitedStreamReaderAsStringCollectionWithFixedBounds.newInstance(is);
    }

    public UnlimitedStreamReaderAsStringCollectionWithLinesBounds withLinesBounds() {
        return UnlimitedStreamReaderAsStringCollectionWithLinesBounds.newInstance(is);
    }
}
