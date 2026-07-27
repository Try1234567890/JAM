package me.tr.trfiles.management.io.streaming.stream;

import me.tr.trfiles.exceptions.CannotRetrieveSizeException;
import me.tr.trfiles.management.io.streaming.Streaming;

import java.io.IOException;
import java.io.InputStream;

public abstract class StreamStreaming<D> extends Streaming<InputStream, D> {

    @Override
    protected int size(InputStream value) {
        try {
            return value.available();
        } catch (IOException e) {
            throw new CannotRetrieveSizeException("Cannot retrieve the size of the stream!");
        }
    }
}
