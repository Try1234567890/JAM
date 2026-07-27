package com.github.trfiles.management.io.streaming.file;

import com.github.trfiles.management.io.streaming.path.PathToPathStreaming;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;

public class FileToPathStreaming extends FileStreaming<Path> {
    private FileToPathStreaming() {
    }

    private record Holder() {
        private static final FileToPathStreaming INSTANCE = new FileToPathStreaming();
    }

    public static FileToPathStreaming getInstance() {
        return Holder.INSTANCE;
    }


    @Override
    protected void writeRangeOrThrown0(@NotNull File value, @NotNull Path out, int from, int to) throws IOException, InvalidPathException {
        PathToPathStreaming.getInstance()
                .writeRangeOrThrown(value.toPath(), out, from, to);
    }
}
