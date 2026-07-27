package me.tr.trfiles.management.io.writer.file;

import me.tr.trfiles.management.io.writer.path.BytesToPathWriter;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.nio.file.InvalidPathException;

public class BytesToFileWriter extends ToFileWriter<byte[]> {
    private BytesToFileWriter() {
    }

    private record Holder() {
        private static final BytesToFileWriter INSTANCE = new BytesToFileWriter();
    }

    public static BytesToFileWriter getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    protected void writeRangeOrThrown0(byte @NotNull [] value, @NotNull File out, int from, int to) throws IOException, InvalidPathException {
        BytesToPathWriter.getInstance()
                .writeRangeOrThrown(value, out.toPath(), from, to);
    }


    @Override
    protected int size(byte[] value) {
        return value.length;
    }
}
