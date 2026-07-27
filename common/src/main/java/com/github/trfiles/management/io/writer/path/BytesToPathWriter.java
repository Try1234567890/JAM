package com.github.trfiles.management.io.writer.path;

import com.github.trfiles.management.io.writer.stream.BytesToStreamWriter;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class BytesToPathWriter extends ToPathWriter<byte[]> {
    private BytesToPathWriter() {
    }

    private record Holder() {
        private static final BytesToPathWriter INSTANCE = new BytesToPathWriter();
    }

    public static BytesToPathWriter getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    protected void writeRangeOrThrown0(byte @NotNull [] value, @NotNull Path out, int from, int to) throws IOException {
        try (OutputStream stream = Files.newOutputStream(out)) {
            BytesToStreamWriter.getInstance()
                    .writeRangeOrThrown(value, stream, from, to);
        }
    }


    @Override
    protected int size(byte[] value) {
        return value.length;
    }
}
