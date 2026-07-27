package com.github.trfiles.management.archives.zipper.zipfile.path;

import com.github.trfiles.management.io.streaming.Streamings;

import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Stream;

public class PathPathParallelZipper extends IPathZipper<Path> {
    private static final PathPathZipper FILE_ZIPPER = PathPathZipper.getInstance();

    private PathPathParallelZipper() {
    }

    private record Holder() {
        private static final PathPathParallelZipper INSTANCE = new PathPathParallelZipper();
    }

    public static PathPathParallelZipper getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    protected void zipOrThrown0(Path zip, Path value) throws IOException {
        if (Files.isRegularFile(zip)) {
            FILE_ZIPPER.zipOrThrown(zip, value);
            return;
        }


        Map<String, String> env = new HashMap<>();
        env.put("create", "true");

        AtomicReference<Throwable> t = new AtomicReference<>(null);

        try (FileSystem zipfs = FileSystems.newFileSystem(zip, env);
             Stream<Path> stream = Files.list(value)) {
            stream.forEach(path -> {
                try {
                    Path fileInZip = zipfs.getPath("/" + path.toString());
                    Streamings.getPathToPathStreaming().writeOrThrown(path, fileInZip);
                } catch (Exception e) {
                    t.set(e);
                }
            });
        }

        Throwable throwable = t.get();
        if (throwable != null)
            throw new IOException("An error occurred while zipping " + value + " inside " + zip, throwable);
    }
}
