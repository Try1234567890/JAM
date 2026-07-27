package com.github.trfiles.management.io.writer.stream;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.OutputStream;

public class CharsToStreamWriter extends ToStreamWriter<char[]> {
    private CharsToStreamWriter() {
    }

    private record Holder() {
        private static final CharsToStreamWriter INSTANCE = new CharsToStreamWriter();
    }

    public static CharsToStreamWriter getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    protected void writeRangeOrThrown0(char @NotNull [] value, @NotNull OutputStream out, int from, int to) throws IOException {

        for (int i = from; i < (value.length - to); i++) {
            int numValue = Character.getNumericValue(value[i]);
            out.write(numValue);
        }

    }


    @Override
    protected int size(char[] value) {
        return value.length;
    }
}
