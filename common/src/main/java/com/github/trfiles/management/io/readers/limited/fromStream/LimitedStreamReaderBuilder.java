package com.github.trfiles.management.io.readers.limited.fromStream;

import com.github.trfiles.management.io.readers.limited.fromStream.asArray.LimitedStreamReaderAsStringArrayBuilder;
import com.github.trfiles.management.io.readers.limited.fromStream.asCollection.LimitedStreamReaderAsStringCollectionBuilder;
import com.github.utilities.validators.Preconditions;

import java.io.InputStream;

public class LimitedStreamReaderBuilder {
    private final InputStream is;

    public LimitedStreamReaderBuilder(InputStream is) {
        this.is = Preconditions.simpleParameterNotNull(is, "is");
    }

    public LimitedStreamReaderAsString asString() {
        return LimitedStreamReaderAsString.newInstance(is);
    }

    public LimitedStreamReaderAsChars asChars() {
        return LimitedStreamReaderAsChars.newInstance(is);
    }

    public LimitedStreamReaderAsBytes asBytes() {
        return LimitedStreamReaderAsBytes.newInstance(is);
    }

    public LimitedStreamReaderAsStringCollectionBuilder asCollection() {
        return new LimitedStreamReaderAsStringCollectionBuilder(is);
    }

    public LimitedStreamReaderAsStringArrayBuilder asArray() {
        return new LimitedStreamReaderAsStringArrayBuilder(is);
    }
}
