package com.github.trfiles.management.io.writer.path;

import com.github.trfiles.management.io.writer.stream.StringToStreamWriter;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class StringToPathWriter extends ToPathWriter<String> {
    private StringToPathWriter() {
    }

    private record Holder() {
        private static final StringToPathWriter INSTANCE = new StringToPathWriter();
    }

    public static StringToPathWriter getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    protected void writeRangeOrThrown0(@NotNull String value, @NotNull Path out, int from, int to) throws IOException {
        try (OutputStream stream = Files.newOutputStream(out)) {
            StringToStreamWriter.getInstance()
                    .writeRangeOrThrown(value, stream, from, to);
        }
    }

    @Override
    protected int size(String value) {
        return value.length();
    }
}
