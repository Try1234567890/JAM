package com.github.trfiles.management.io.readers.limited.fromStream.asCollection;


import com.github.trfiles.management.io.readers.unlimited.fromStream.asCollection.UnlimitedStreamReaderAsStringCollection;
import com.github.trfiles.management.io.readers.unlimited.fromStream.asCollection.UnlimitedStreamReaderAsStringCollectionWithFixedBounds;
import com.github.trfiles.management.io.readers.unlimited.fromStream.asCollection.UnlimitedStreamReaderAsStringCollectionWithLinesBounds;
import com.github.utilities.validators.Preconditions;

import java.io.InputStream;

public class LimitedStreamReaderAsStringCollectionBuilder {
    private final InputStream is;

    public LimitedStreamReaderAsStringCollectionBuilder(InputStream is) {
        this.is = Preconditions.simpleParameterNotNull(is, "is");
    }

    public LimitedStreamReaderAsStringCollection withFixedBounds() {
        return LimitedStreamReaderAsStringCollectionWithFixedBounds.newInstance(is);
    }

    public LimitedStreamReaderAsStringCollection withLinesBounds() {
        return LimitedStreamReaderAsStringCollectionWithLinesBounds.newInstance(is);
    }
}
