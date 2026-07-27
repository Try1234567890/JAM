package com.github.trfiles.management.archives.zipper.zipfile.file;

import java.io.File;
import java.io.IOException;
import java.nio.file.InvalidPathException;

public class FileFileParallelZipper extends IFileZipper<File> {
    private FileFileParallelZipper() {
    }

    private record Holder() {
        private static final FileFileParallelZipper INSTANCE = new FileFileParallelZipper();
    }

    public static FileFileParallelZipper getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    public void zipOrThrown(File zip, File value) throws IOException, InvalidPathException {
        FilePathParallelZipper.getInstance()
                .zipOrThrown(zip, value.toPath());
    }
}
