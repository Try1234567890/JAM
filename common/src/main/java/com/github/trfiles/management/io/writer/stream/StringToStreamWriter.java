package com.github.trfiles.management.io.writer.stream;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.OutputStream;

public class StringToStreamWriter extends ToStreamWriter<String> {
    private static final CharsToStreamWriter writer = CharsToStreamWriter.getInstance();
    private StringToStreamWriter() {
    }

    private record Holder() {
        private static final StringToStreamWriter INSTANCE = new StringToStreamWriter();
    }

    public static StringToStreamWriter getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    protected void writeRangeOrThrown0(@NotNull String value, @NotNull OutputStream out, int from, int to) throws IOException {
        String subString = value.substring(from, to);
        char[] toChars = subString.toCharArray();

        writer.writeOrThrown(toChars, out);
    }

    @Override
    protected int size(String value) {
        return value.length();
    }
}
