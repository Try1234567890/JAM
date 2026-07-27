package com.github.trfiles.management.io.reader.file;

import com.github.trfiles.management.io.reader.Reader;

import java.io.File;

public abstract class FileReader<R> extends Reader<File, R> {

    @Override
    protected int size(File value) {
        long size = value.length();
        return size > Integer.MAX_VALUE ? Integer.MAX_VALUE : (int) size;
    }
}
