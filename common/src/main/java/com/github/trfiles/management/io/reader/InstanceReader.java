package com.github.trfiles.management.io.reader;

import com.github.utilities.validators.Preconditions;

import java.io.IOException;
import java.util.Optional;

public record InstanceReader<I, R>(I input, Reader<I, R> reader) {

    public InstanceReader(I input, Reader<I, R> reader) {
        this.input = Preconditions.parameterNotNull(input, "input");
        this.reader = Preconditions.parameterNotNull(reader, "reader");
    }

    public R readOrThrown(int from, int to) throws IOException {
        return reader.readOrThrown(input, from, to);
    }

    public R readOrThrown(int from) throws IOException {
        return reader.readOrThrown(input, from);
    }

    public R readOrThrown() throws IOException {
        return reader.readOrThrown(input);
    }

    public Optional<R> read(int from, int to) {
        return reader.read(input, from, to);
    }

    public Optional<R> read(int from) {
        return reader.read(input, from);
    }

    public Optional<R> read() {
        return reader.read(input);
    }

    public R readOrNull(int from, int to) {
        return reader.readOrNull(input, from, to);
    }

    public R readOrNull(I input, int from) {
        return reader.readOrNull(input, from);
    }

    public R readOrNull() {
        return reader.readOrNull(input);
    }

    public R readOrDefault(int from, int to, R def) {
        return reader.readOrDefault(input, from, to, def);
    }

    public R readOrDefault(int from, R def) {
        return reader.readOrDefault(input, from, def);
    }

    public R readOrDefault(R def) {
        return reader.readOrDefault(input, def);
    }
}
