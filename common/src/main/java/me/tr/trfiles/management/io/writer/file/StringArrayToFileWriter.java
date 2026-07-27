package me.tr.trfiles.management.io.writer.file;

import me.tr.trfiles.management.io.writer.path.StringArrayToPathWriter;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.nio.file.InvalidPathException;

public class StringArrayToFileWriter extends ToFileWriter<String[]> {
    private StringArrayToFileWriter() {
    }

    private record Holder() {
        private static final StringArrayToFileWriter INSTANCE = new StringArrayToFileWriter();
    }

    public static StringArrayToFileWriter getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    protected void writeRangeOrThrown0(String @NotNull [] value, @NotNull File out, int from, int to) throws IOException, InvalidPathException {
        StringArrayToPathWriter.getInstance()
                .writeRangeOrThrown(value, out.toPath(), from, to);
    }

    @Override
    protected int size(String[] value) {
        return value.length;
    }
}
