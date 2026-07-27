package com.github.trfiles.management.io.writer.path;

import com.github.trfiles.management.io.writer.Writer;

import java.io.IOException;
import java.nio.file.Path;

public abstract class ToPathWriter<V> extends Writer<V, Path> {

    @Override
    public void writeRangeOrThrown(V value, Path out, int from, int to) throws IOException {
        checkWritable(out);
        super.writeRangeOrThrown(value, out, from, to);
    }
}
