package com.github.trfiles.management.io.writer;

import com.github.utilities.validators.Preconditions;

import java.io.IOException;

public record InstanceWriter<V, O>(O output, Writer<V, O> writer) {

    public InstanceWriter(O output, Writer<V, O> writer) {
        this.output = Preconditions.parameterNotNull(output, "output");
        this.writer = Preconditions.parameterNotNull(writer, "writer");
    }

    public void writeRangeOrThrown(V value, int from, int to) throws IOException {
        writer().writeRangeOrThrown(value, output(), from, to);
    }

    public void writeFromOrThrown(V value, int from) throws IOException {
        writer().writeFromOrThrown(value, output(), from);
    }

    public void writeToOrThrown(V value, int to) throws IOException {
        writer().writeToOrThrown(value, output(), to);
    }

    public void writeOrThrown(V value) throws IOException {
        writer().writeOrThrown(value, output());
    }

    public boolean writeRange(V value, int from, int to) {
        return writer().writeRange(value, output(), from, to);
    }

    public boolean writeFrom(V value, int from) {
        return writer().writeFrom(value, output(), from);
    }

    public boolean writeTo(V value, int to) {
        return writer().writeTo(value, output(), to);
    }

    public boolean write(V value) {
        return writer().write(value, output());
    }
}
