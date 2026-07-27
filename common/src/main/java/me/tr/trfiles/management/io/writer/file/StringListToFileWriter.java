package me.tr.trfiles.management.io.writer.file;

import me.tr.trfiles.management.io.writer.path.StringListToPathWriter;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.util.List;

public class StringListToFileWriter extends ToFileWriter<List<String>> {
    private StringListToFileWriter() {
    }

    private record Holder() {
        private static final StringListToFileWriter INSTANCE = new StringListToFileWriter();
    }

    public static StringListToFileWriter getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    protected void writeRangeOrThrown0(@NotNull List<String> value, @NotNull File out, int from, int to) throws IOException, InvalidPathException {
        StringListToPathWriter.getInstance()
                .writeRangeOrThrown(value, out.toPath(), from, to);
    }

    @Override
    protected int size(List<String> value) {
        return value.size();
    }
}
