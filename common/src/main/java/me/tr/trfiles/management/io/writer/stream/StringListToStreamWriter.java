package me.tr.trfiles.management.io.writer.stream;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

public class StringListToStreamWriter extends ToStreamWriter<List<String>> {
    private static final StringToStreamWriter writer = StringToStreamWriter.getInstance();
    private StringListToStreamWriter() {
    }

    private record Holder() {
        private static final StringListToStreamWriter INSTANCE = new StringListToStreamWriter();
    }

    public static StringListToStreamWriter getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    protected void writeRangeOrThrown0(@NotNull List<String> value, @NotNull OutputStream out, int from, int to) throws IOException {
        List<String> subString = value.subList(from, to);

        for (String string : subString) {
            writer.write(string, out);
        }

    }

    @Override
    protected int size(List<String> value) {
        return value.size();
    }
}
