package me.tr.trfiles.management.io.writer.path;

import me.tr.trfiles.exceptions.CannotRetrieveSizeException;
import me.tr.trfiles.management.io.writer.stream.StreamToStreamWriter;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class StreamToPathWriter extends ToPathWriter<InputStream> {
    private StreamToPathWriter() {
    }

    private record Holder() {
        private static final StreamToPathWriter INSTANCE = new StreamToPathWriter();
    }

    public static StreamToPathWriter getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    protected void writeRangeOrThrown0(@NotNull InputStream value, @NotNull Path out, int from, int to) throws IOException {
        try (OutputStream stream = Files.newOutputStream(out)) {
            StreamToStreamWriter.getInstance()
                    .writeRangeOrThrown(value, stream, from, to);
        }
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
