package com.github.trfiles.management.io.streaming.file;

import com.github.trfiles.management.io.streaming.Streaming;

import java.io.File;

public abstract class FileStreaming<D> extends Streaming<File, D> {

    @Override
    protected int size(File value) {
        long size = value.length();
        return size > Integer.MAX_VALUE ? Integer.MAX_VALUE : (int) size;
    }
}
