package com.github.trfiles.management.io.readers.unlimited.fromPath.asArray;

import com.github.trfiles.management.io.readers.unlimited.fromPath.UnlimitedPathReader;

import java.nio.file.Path;
import java.util.Collection;

public abstract class UnlimitedPathReaderAsStringArray extends UnlimitedPathReader<Collection<String[]>> {

    protected UnlimitedPathReaderAsStringArray(Path input) {
        super(input);
    }

    @Override
    public abstract StringArrayPathReaderConfiguration getConfiguration();

    public abstract static class StringArrayPathReaderConfiguration extends PathReaderConfiguration<Collection<String[]>> {
    }
}
