package com.github.trfiles.management.io.readers.limited.fromStream.asArray;

import com.github.trfiles.management.io.readers.unlimited.fromStream.asArray.UnlimitedStreamReaderAsStringArray;
import com.github.trfiles.management.io.readers.unlimited.fromStream.asArray.UnlimitedStreamReaderAsStringArrayWithFixedBounds;
import com.github.trfiles.management.io.readers.unlimited.fromStream.asArray.UnlimitedStreamReaderAsStringArrayWithLinesBounds;
import com.github.utilities.validators.Preconditions;

import java.io.InputStream;

public class LimitedStreamReaderAsStringArrayBuilder {
    private final InputStream is;

    public LimitedStreamReaderAsStringArrayBuilder(InputStream is) {
        this.is = Preconditions.simpleParameterNotNull(is, "is");
    }

    public LimitedStreamReaderAsStringArray withFixedBounds() {
        return LimitedStreamReaderAsStringArrayWithFixedBounds.newInstance(is);
    }

    public LimitedStreamReaderAsStringArray withLinesBounds() {
        return LimitedStreamReaderAsStringArrayWithLinesBounds.newInstance(is);
    }
}
