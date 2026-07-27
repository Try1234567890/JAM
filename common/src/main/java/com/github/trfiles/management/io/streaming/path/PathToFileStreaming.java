package com.github.trfiles.management.io.streaming.path;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

public class PathToFileStreaming extends PathStreaming<File> {
    private PathToFileStreaming() {
    }

    private record Holder() {
        private static final PathToFileStreaming INSTANCE = new PathToFileStreaming();
    }

    public static PathToFileStreaming getInstance() {
        return Holder.INSTANCE;
    }


    @Override
    protected void writeRangeOrThrown0(@NotNull Path value, @NotNull File out, int from, int to) throws IOException {
        Path newOut = out.toPath();
        PathToPathStreaming.getInstance()
                .writeRangeOrThrown(value, newOut, from, to);
    }
}
