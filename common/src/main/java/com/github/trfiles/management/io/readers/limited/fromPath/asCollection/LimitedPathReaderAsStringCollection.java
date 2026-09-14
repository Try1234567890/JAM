package com.github.trfiles.management.io.readers.limited.fromPath.asCollection;

import com.github.trfiles.management.io.readers.limited.fromPath.LimitedPathReader;

import java.nio.file.Path;
import java.util.Collection;

public abstract class LimitedPathReaderAsStringCollection extends LimitedPathReader<Collection<String>> {

    protected LimitedPathReaderAsStringCollection(Path input) {
        super(input);
    }

    @Override
    public abstract StringCollectionPathReaderConfiguration getConfiguration();

    public abstract static class StringCollectionPathReaderConfiguration extends PathReaderConfiguration<Collection<String>> {

    }
}
