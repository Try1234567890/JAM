package me.tr.trfiles.management.io.reader.stream;

import me.tr.trfiles.exceptions.CannotRetrieveSizeException;
import me.tr.trfiles.management.io.reader.Reader;

import java.io.IOException;
import java.io.InputStream;

public abstract class StreamReader<R> extends Reader<InputStream, R> {

    @Override
    protected int size(InputStream value) {
        try {
            return value.available();
        } catch (IOException e) {
            throw new CannotRetrieveSizeException("Cannot retrieve the size of the stream!");
        }
    }
}
