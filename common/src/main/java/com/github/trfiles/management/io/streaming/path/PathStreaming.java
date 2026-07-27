package com.github.trfiles.management.io.streaming.path;

import com.github.trfiles.exceptions.CannotRetrieveSizeException;
import com.github.trfiles.management.io.streaming.Streaming;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public abstract class PathStreaming<D> extends Streaming<Path, D> {

    @Override
    protected int size(Path value) {
        try {
            long size = Files.size(value);
            return size > Integer.MAX_VALUE ? Integer.MAX_VALUE : (int) size;
        } catch (IOException e) {
            throw new CannotRetrieveSizeException("Cannot retrieve the size of the path " + value);
        }
    }
}
