package me.tr.trfiles.management.archives.zipper.zipfile.file;

import me.tr.trfiles.management.archives.zipper.zipfile.path.PathPathZipper;

import java.io.File;
import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;

public class FilePathZipper extends IFileZipper<Path> {

    private FilePathZipper() {
    }

    private record Holder() {
        private static final FilePathZipper INSTANCE = new FilePathZipper();
    }

    public static FilePathZipper getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    public void zipOrThrown(File zip, Path value) throws IOException, InvalidPathException {
        PathPathZipper.getInstance()
                .zipOrThrown(zip.toPath(), value);
    }
}
