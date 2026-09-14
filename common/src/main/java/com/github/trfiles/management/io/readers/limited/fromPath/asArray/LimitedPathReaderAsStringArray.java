package com.github.trfiles.management.io.readers.limited.fromPath.asArray;

import com.github.trfiles.management.io.readers.limited.fromPath.LimitedPathReader;

import java.nio.file.Path;

public abstract class LimitedPathReaderAsStringArray extends LimitedPathReader<String[]> {

    protected LimitedPathReaderAsStringArray(Path input) {
        super(input);
    }

    @Override
    public abstract StringArrayPathReaderConfiguration getConfiguration();

    public abstract static class StringArrayPathReaderConfiguration extends PathReaderConfiguration<String[]> {
    }
}
