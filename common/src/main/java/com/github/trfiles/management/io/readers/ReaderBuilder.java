package com.github.trfiles.management.io.readers;

import com.github.trfiles.management.io.readers.limited.LimitedReaderBuilder;
import com.github.trfiles.management.io.readers.unlimited.UnlimitedReaderBuilder;

public class ReaderBuilder {
    private ReaderBuilder() {
    }

    private static final class Holder {
        private static final ReaderBuilder INSTANCE = new ReaderBuilder();
    }

    public static ReaderBuilder get() {
        return Holder.INSTANCE;
    }

    public UnlimitedReaderBuilder unlimited() {
        return UnlimitedReaderBuilder.get();
    }

    public LimitedReaderBuilder limited() {
        return LimitedReaderBuilder.get();
    }
}
