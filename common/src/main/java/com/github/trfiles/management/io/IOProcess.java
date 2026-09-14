package com.github.trfiles.management.io;

import com.github.trfiles.utility.ThrowableCollector;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * An IOProcess is the base interface for all systems that
 * performs IOs operations. These, by default, are inside
 * the package: {@code com.github.trfiles.management}. These systems
 * include: Readers, Writers, Streamers, Downloaders, Uploaders, Zipper, Unzipper.
 *
 * @param <I> the input of the process.
 * @param <O> the output of the process.
 */
public abstract class IOProcess<I, O> {
    protected ThrowableCollector exceptions = new ThrowableCollector();

    public ThrowableCollector getExceptions() {
        return exceptions;
    }


    /**
     * Retrieve the size of the {@code input value} given.<p>
     *
     * @param value The value to retrieve value for.
     * @return The size of the value.
     * @throws Exception If an error occurs while retrieve the size.
     */
    protected abstract long _size(I value) throws Exception;

    public long size(I value) throws IOException {
        try {
            return _size(value);
        } catch (Throwable t) {
            throw (t instanceof IOException e ? e : new IOException(t));
        }
    }

    protected void checkIndexes(I value, long offset, long length) throws IOException {
        if (offset < 0) {
            throw new IndexOutOfBoundsException("The \"offset\" index is less than 0!");
        }

        long size = size(value);
        if (length > size) {
            throw new IndexOutOfBoundsException("The \"length\" index is greater than the size of the value!");
        }
        if (offset > length) {
            throw new IndexOutOfBoundsException("The \"offset\" index is greater than the \"length\" index!");
        }
    }

    private void checkFile(Path value) {
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
        checkFile(value);
        if (!Files.isWritable(value)) {
            throw new IllegalArgumentException("The path " + value + " is not writable!");
        }
    }

}
