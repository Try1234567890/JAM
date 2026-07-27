package me.tr.trfiles.management.io.writer.file;

import me.tr.trfiles.management.io.writer.path.CharsToPathWriter;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.nio.file.InvalidPathException;

public class CharsToFileWriter extends ToFileWriter<char[]> {
    private CharsToFileWriter() {
    }

    private record Holder() {
        private static final CharsToFileWriter INSTANCE = new CharsToFileWriter();
    }

    public static CharsToFileWriter getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    protected void writeRangeOrThrown0(char @NotNull [] value, @NotNull File out, int from, int to) throws IOException, InvalidPathException {
        CharsToPathWriter.getInstance()
                .writeRangeOrThrown(value, out.toPath(), from, to);
    }


    @Override
    protected int size(char[] value) {
        return value.length;
    }
}
