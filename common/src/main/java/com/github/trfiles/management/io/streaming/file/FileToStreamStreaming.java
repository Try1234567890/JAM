package com.github.trfiles.management.io.streaming.file;

import com.github.trfiles.management.io.streaming.path.PathToStreamStreaming;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.InvalidPathException;

public class FileToStreamStreaming extends FileStreaming<OutputStream> {
    private FileToStreamStreaming() {
    }

    private record Holder() {
        private static final FileToStreamStreaming INSTANCE = new FileToStreamStreaming();
    }

    public static FileToStreamStreaming getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    protected void writeRangeOrThrown0(@NotNull File value, @NotNull OutputStream out, int from, int to) throws IOException, InvalidPathException {
        PathToStreamStreaming.getInstance()
                .writeRangeOrThrown(value.toPath(), out, from, to);
    }
}
