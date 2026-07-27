package com.github.trfiles.management.io.streaming;

import com.github.utilities.validators.Preconditions;

import java.io.IOException;

public record InstanceStreaming<I, O>(I input, O output, Streaming<I, O> streaming) {

    public InstanceStreaming(I input, O output, Streaming<I, O> streaming) {
        this.input = Preconditions.parameterNotNull(input, "input");
        this.output = Preconditions.parameterNotNull(output, "output");
        this.streaming = Preconditions.parameterNotNull(streaming, "streaming");
    }

    public void writeRangeOrThrown(int from, int to) throws IOException {
        streaming.writeRangeOrThrown(input, output, from, to);
    }

    public void writeFromOrThrown(int from) throws IOException {
        streaming.writeFromOrThrown(input, output, from);
    }

    public void writeToOrThrown(int to) throws IOException {
        streaming.writeToOrThrown(input, output, to);
    }

    public void writeOrThrown() throws IOException {
        streaming.writeOrThrown(input, output);
    }

    public boolean writeRange(int from, int to) {
        return streaming.writeRange(input, output, from, to);
    }

    public boolean writeFrom(int from) {
        return streaming.writeFrom(input, output, from);
    }

    public boolean writeTo(int to) {
        return streaming.writeTo(input, output, to);
    }

    public boolean write() {
        return streaming.write(input, output);
    }
}
