package me.tr.trfiles.management.io.streaming.file;

import me.tr.trfiles.management.io.streaming.path.PathToPathStreaming;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.nio.file.InvalidPathException;

public class FileToFileStreaming extends FileStreaming<File> {
    private FileToFileStreaming() {
    }

    private record Holder() {
        private static final FileToFileStreaming INSTANCE = new FileToFileStreaming();
    }

    public static FileToFileStreaming getInstance() {
        return Holder.INSTANCE;
    }


    @Override
    protected void writeRangeOrThrown0(@NotNull File value, @NotNull File out, int from, int to) throws IOException, InvalidPathException {
        PathToPathStreaming.getInstance()
                .writeRangeOrThrown(value.toPath(), out.toPath(), from, to);
    }
}
