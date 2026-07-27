package com.github.trfiles.management.io.streaming.path;

import com.github.trfiles.management.io.streaming.stream.StreamToStreamStreaming;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class PathToStreamStreaming extends PathStreaming<OutputStream> {
    private PathToStreamStreaming() {
    }

    private record Holder() {
        private static final PathToStreamStreaming INSTANCE = new PathToStreamStreaming();
    }

    public static PathToStreamStreaming getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    protected void writeRangeOrThrown0(@NotNull Path value, @NotNull OutputStream out, int from, int to) throws IOException {
        checkReadable(value);
        try (InputStream in = Files.newInputStream(value)) {
            StreamToStreamStreaming.getInstance()
                    .writeRangeOrThrown(in, out, from, to);
        }
    }
}
