package me.tr.trfiles.management.io.streaming.stream;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class StreamToPathStreaming extends StreamStreaming<Path> {
    private StreamToPathStreaming() {
    }

    private record Holder() {
        private static final StreamToPathStreaming INSTANCE = new StreamToPathStreaming();
    }

    public static StreamToPathStreaming getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    protected void writeRangeOrThrown0(@NotNull InputStream inStream, @NotNull Path out, int from, int to) throws IOException {
        checkWritable(out);
        try (OutputStream outStream = Files.newOutputStream(out)) {
            StreamToStreamStreaming.getInstance()
                    .writeRangeOrThrown(inStream, outStream, from, to);
        }
    }
}
