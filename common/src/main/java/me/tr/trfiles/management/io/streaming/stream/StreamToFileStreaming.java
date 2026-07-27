package me.tr.trfiles.management.io.streaming.stream;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;

public class StreamToFileStreaming extends StreamStreaming<File> {
    private StreamToFileStreaming() {
    }

    private record Holder() {
        private static final StreamToFileStreaming INSTANCE = new StreamToFileStreaming();
    }

    public static StreamToFileStreaming getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    protected void writeRangeOrThrown0(@NotNull InputStream value, @NotNull File out, int from, int to) throws IOException, InvalidPathException {
        Path path = out.toPath();
        StreamToPathStreaming.getInstance()
                .writeRangeOrThrown(value, path, from, to);
    }
}
