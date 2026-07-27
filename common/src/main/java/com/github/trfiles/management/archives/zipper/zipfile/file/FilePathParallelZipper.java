package com.github.trfiles.management.archives.zipper.zipfile.file;

import com.github.trfiles.management.archives.zipper.zipfile.path.PathPathParallelZipper;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;

public class FilePathParallelZipper extends IFileZipper<Path> {
    private FilePathParallelZipper() {
    }

    private record Holder() {
        private static final FilePathParallelZipper INSTANCE = new FilePathParallelZipper();
    }

    public static FilePathParallelZipper getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    public void zipOrThrown(File zip, Path value) throws IOException, InvalidPathException {
        PathPathParallelZipper.getInstance()
                .zipOrThrown(zip.toPath(), value);
    }
}
