package com.github.trfiles.management.io.readers.unlimited.fromPath.asCollection;

import com.github.trfiles.management.io.readers.unlimited.fromPath.UnlimitedPathReader;

import java.nio.file.Path;
import java.util.Collection;

public abstract class UnlimitedPathReaderAsStringCollection extends UnlimitedPathReader<Collection<Collection<String>>> {

    protected UnlimitedPathReaderAsStringCollection(Path input) {
        super(input);
    }

    @Override
    public abstract StringCollectionPathReaderConfiguration getConfiguration();

    public abstract static class StringCollectionPathReaderConfiguration extends PathReaderConfiguration<Collection<Collection<String>>> {

    }
}
