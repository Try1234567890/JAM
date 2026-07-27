package com.github.trfiles.management.io.writer.path;

import com.github.trfiles.management.io.writer.stream.StringListToStreamWriter;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class StringListToPathWriter extends ToPathWriter<List<String>> {
    private StringListToPathWriter() {
    }

    private record Holder() {
        private static final StringListToPathWriter INSTANCE = new StringListToPathWriter();
    }

    public static StringListToPathWriter getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    protected void writeRangeOrThrown0(@NotNull List<String> value, @NotNull Path out, int from, int to) throws IOException {
        try (OutputStream stream = Files.newOutputStream(out)) {
            StringListToStreamWriter.getInstance()
                    .writeRangeOrThrown(value, stream, from, to);
        }
    }

    @Override
    protected int size(List<String> value) {
        return value.size();
    }
}
