package me.tr.trfiles.management.io.writer.stream;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.OutputStream;

public class StringArrayToStreamWriter extends ToStreamWriter<String[]> {
    private static final StringToStreamWriter writer = StringToStreamWriter.getInstance();
    private StringArrayToStreamWriter() {
    }

    private record Holder() {
        private static final StringArrayToStreamWriter INSTANCE = new StringArrayToStreamWriter();
    }

    public static StringArrayToStreamWriter getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    protected void writeRangeOrThrown0(String @NotNull [] value, @NotNull OutputStream out, int from, int to) throws IOException {

        for (int i = from; i < (value.length - to); i++) {
            writer.writeOrThrown(value[i], out);
        }
    }

    @Override
    protected int size(String[] value) {
        return value.length;
    }
}
