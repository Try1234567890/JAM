package com.github.trfiles.management.io.writer.file;

import com.github.trfiles.management.io.writer.path.StringToPathWriter;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.nio.file.InvalidPathException;

public class StringToFileWriter extends ToFileWriter<String> {
    private StringToFileWriter() {
    }

    private record Holder() {
        private static final StringToFileWriter INSTANCE = new StringToFileWriter();
    }

    public static StringToFileWriter getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    protected void writeRangeOrThrown0(@NotNull String value, @NotNull File out, int from, int to) throws IOException, InvalidPathException {
        StringToPathWriter.getInstance()
                .writeRangeOrThrown(value, out.toPath(), from, to);
    }

    @Override
    protected int size(String value) {
        return value.length();
    }
}
