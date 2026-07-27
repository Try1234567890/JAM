package com.github.trfiles.management.io.reader;

import com.github.utilities.validators.Preconditions;
import com.github.trfiles.management.io.IOProcess;

import java.io.IOException;
import java.util.Optional;

public abstract class Reader<I, R> extends IOProcess<I> {
    protected abstract R readOrThrown0(I input, int from, int to) throws IOException;

    public R readOrThrown(I input, int from, int to) throws IOException {
        Preconditions.parameterNotNull(input, "input");

        checkIndexes(input, from, to);
        return readOrThrown0(input, from, to);
    }

    public R readOrThrown(I input, int from) throws IOException {
        return readOrThrown(input, from, size(input));
    }

    public R readOrThrown(I input) throws IOException {
        return readOrThrown(input, 0);
    }

    public Optional<R> read(I input, int from, int to) {
        try {
            return Optional.of(readOrThrown(input, from, to));
        } catch (IOException ignore) {
        }
        return Optional.empty();
    }

    public Optional<R> read(I input, int from) {
        return read(input, from, size(input));
    }

    public Optional<R> read(I input) {
        return read(input, 0);
    }

    public R readOrNull(I input, int from, int to) {
        return read(input, from, to).orElse(null);
    }

    public R readOrNull(I input, int from) {
        return readOrNull(input, from, size(input));
    }

    public R readOrNull(I input) {
        return readOrNull(input, 0);
    }

    public R readOrDefault(I input, int from, int to, R def) {
        return read(input, from, to).orElse(def);
    }

    public R readOrDefault(I input, int from, R def) {
        return readOrDefault(input, from, size(input), def);
    }

    public R readOrDefault(I input, R def) {
        return readOrDefault(input, 0, def);
    }
}
