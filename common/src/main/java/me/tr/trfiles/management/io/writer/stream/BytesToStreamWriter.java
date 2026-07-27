package me.tr.trfiles.management.io.writer.stream;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.OutputStream;

public class BytesToStreamWriter extends ToStreamWriter<byte[]> {
    private BytesToStreamWriter() {
    }

    private record Holder() {
        private static final BytesToStreamWriter INSTANCE = new BytesToStreamWriter();
    }

    public static BytesToStreamWriter getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    protected void writeRangeOrThrown0(byte @NotNull [] value, @NotNull OutputStream out, int from, int to) throws IOException {

        for (int i = from; i < (value.length - to); i++) {
            out.write(value[i]);
        }

    }


    @Override
    protected int size(byte[] value) {
        return value.length;
    }
}
