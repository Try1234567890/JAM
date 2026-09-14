package com.github.trfiles.management.io.readers.unlimited.fromStream;

import com.github.trfiles.management.io.readers.unlimited.fromStream.asArray.UnlimitedStreamReaderAsStringArrayBuilder;
import com.github.trfiles.management.io.readers.unlimited.fromStream.asCollection.UnlimitedStreamReaderAsStringCollectionBuilder;
import com.github.utilities.validators.Preconditions;

import java.io.InputStream;

public class UnlimitedStreamReaderBuilder {
    private final InputStream is;

    public UnlimitedStreamReaderBuilder(InputStream is) {
        this.is = Preconditions.simpleParameterNotNull(is, "is");
    }

    public UnlimitedStreamReaderAsString asString() {
        return UnlimitedStreamReaderAsString.newInstance(is);
    }

    public UnlimitedStreamReaderAsChars asChars() {
        return UnlimitedStreamReaderAsChars.newInstance(is);
    }

    public UnlimitedStreamReaderAsBytes asBytes() {
        return UnlimitedStreamReaderAsBytes.newInstance(is);
    }

    public UnlimitedStreamReaderAsStringCollectionBuilder asCollection() {
        return new UnlimitedStreamReaderAsStringCollectionBuilder(is);
    }

    public UnlimitedStreamReaderAsStringArrayBuilder asArray() {
        return new UnlimitedStreamReaderAsStringArrayBuilder(is);
    }
}
