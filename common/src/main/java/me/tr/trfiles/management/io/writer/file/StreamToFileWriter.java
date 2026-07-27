package me.tr.trfiles.management.io.writer.file;

import me.tr.trfiles.exceptions.CannotRetrieveSizeException;
import me.tr.trfiles.management.io.writer.path.StreamToPathWriter;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.InvalidPathException;

public class StreamToFileWriter extends ToFileWriter<InputStream> {
    private StreamToFileWriter() {
    }

    private record Holder() {
        private static final StreamToFileWriter INSTANCE = new StreamToFileWriter();
    }

    public static StreamToFileWriter getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    protected void writeRangeOrThrown0(@NotNull InputStream value, @NotNull File out, int from, int to) throws IOException, InvalidPathException {
        StreamToPathWriter.getInstance()
                .writeRangeOrThrown(value, out.toPath(), from, to);
    }

    @Override
    protected int size(InputStream value) {
        try {
            return value.available();
        } catch (IOException e) {
            throw new CannotRetrieveSizeException("Cannot retrieve the size of the input stream!");
        }
    }
}
