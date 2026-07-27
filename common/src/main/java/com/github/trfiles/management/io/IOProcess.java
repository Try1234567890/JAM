package com.github.trfiles.management.io;

import com.github.trfiles.management.FileCreator;

import java.nio.file.Files;
import java.nio.file.Path;

public abstract class IOProcess<I> {

    protected abstract int size(I value);

    protected void checkIndexes(I value, int from, int to) {
        int size = size(value);
        if (from < 0) {
            throw new IndexOutOfBoundsException("The \"from\" index is less than 0!");
        }
        if (to > size) {
            throw new IndexOutOfBoundsException("The \"to\" index is greater than the size of the value!");
        }
        if (from > to) {
            throw new IndexOutOfBoundsException("The \"from\" index is greater than the \"to\" index!");
        }
    }

    protected void checkFile(Path value) {
        if (!Files.exists(value)) {
            throw new IllegalArgumentException("The path " + value + " doesn't exists!");
        }
        if (!Files.isRegularFile(value)) {
            throw new IllegalArgumentException("The path " + value + " is not a regular file!");
        }
    }

    protected void checkReadable(Path value) {
        checkFile(value);
        if (!Files.isReadable(value)) {
            throw new IllegalArgumentException("The path " + value + " is not readable!");
        }
    }

    protected void checkWritable(Path value) {
        if (!Files.exists(value)) {
            Throwable error = FileCreator.newFile(value).error();
            if (error != null) {
                throw new IllegalArgumentException("The path " + value + " doesn't exists and cannot be created!", error);
            }
        }
        checkFile(value);
        if (!Files.isWritable(value)) {
            throw new IllegalArgumentException("The path " + value + " is not writable!");
        }
    }

}
