package com.github.trfiles.management.io.streaming.path;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class PathToPathStreaming extends PathStreaming<Path> {
    private PathToPathStreaming() {
    }

    private record Holder() {
        private static final PathToPathStreaming INSTANCE = new PathToPathStreaming();
    }

    public static PathToPathStreaming getInstance() {
        return Holder.INSTANCE;
    }


    @Override
    protected void writeRangeOrThrown0(@NotNull Path value, @NotNull Path out, int from, int to) throws IOException {
        checkWritable(out);
        try (OutputStream outStream = Files.newOutputStream(out)) {
            PathToStreamStreaming.getInstance()
                    .writeRangeOrThrown(value, outStream, from, to);
        }
    }
}
