package com.github.trfiles.management.io.writer.file;

import com.github.trfiles.management.io.writer.Writer;

import java.io.File;
import java.io.IOException;
import java.nio.file.InvalidPathException;

public abstract class ToFileWriter<V> extends Writer<V, File> {

    @Override
    public void writeRangeOrThrown(V value, File out, int from, int to) throws IOException, InvalidPathException {
        checkWritable(out.toPath());
        super.writeRangeOrThrown(value, out, from, to);
    }

    public void writeFromOrThrown(V value, File out, int from) throws IOException, InvalidPathException {
        super.writeFromOrThrown(value, out, from);
    }

    public void writeToOrThrown(V value, File out, int to) throws IOException, InvalidPathException {
        super.writeToOrThrown(value, out, to);
    }

    public void writeOrThrown(V value, File out) throws IOException, InvalidPathException {
        super.writeOrThrown(value, out);
    }

    public boolean writeRange(V value, File out, int from, int to) throws InvalidPathException {
        return super.writeRange(value, out, from, to);
    }

    public boolean writeFrom(V value, File out, int from) throws InvalidPathException {
        return super.writeFrom(value, out, from);
    }

    public boolean writeTo(V value, File out, int to) throws InvalidPathException {
        return super.writeTo(value, out, to);
    }

    public boolean write(V value, File out) throws InvalidPathException {
        return super.write(value, out);
    }

}
