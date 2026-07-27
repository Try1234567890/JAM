package com.github.trfiles.management.io.writer.path;

import com.github.trfiles.management.io.writer.stream.CharsToStreamWriter;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class CharsToPathWriter extends ToPathWriter<char[]> {
    private CharsToPathWriter() {
    }

    private record Holder() {
        private static final CharsToPathWriter INSTANCE = new CharsToPathWriter();
    }

    public static CharsToPathWriter getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    protected void writeRangeOrThrown0(char @NotNull [] value, @NotNull Path out, int from, int to) throws IOException {
        try (OutputStream stream = Files.newOutputStream(out)) {
            CharsToStreamWriter.getInstance()
                    .writeRangeOrThrown(value, stream, from, to);
        }
    }


    @Override
    protected int size(char[] value) {
        return value.length;
    }
}
