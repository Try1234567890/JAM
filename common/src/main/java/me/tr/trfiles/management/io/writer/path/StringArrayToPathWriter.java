package me.tr.trfiles.management.io.writer.path;

import me.tr.trfiles.management.io.writer.stream.StringArrayToStreamWriter;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class StringArrayToPathWriter extends ToPathWriter<String[]> {
    private StringArrayToPathWriter() {
    }

    private record Holder() {
        private static final StringArrayToPathWriter INSTANCE = new StringArrayToPathWriter();
    }

    public static StringArrayToPathWriter getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    protected void writeRangeOrThrown0(String @NotNull [] value, @NotNull Path out, int from, int to) throws IOException {
        try (OutputStream stream = Files.newOutputStream(out)) {
            StringArrayToStreamWriter.getInstance()
                    .writeRangeOrThrown(value, stream, from, to);
        }
    }

    @Override
    protected int size(String[] value) {
        return value.length;
    }
}
