package me.tr.trfiles.management.archives.zipper.zipfile.file;

import me.tr.trfiles.management.archives.zipper.zipfile.path.PathFileZipper;

import java.io.File;
import java.io.IOException;
import java.nio.file.InvalidPathException;

public class FileFileZipper extends IFileZipper<File> {

    private FileFileZipper() {
    }

    private record Holder() {
        private static final FileFileZipper INSTANCE = new FileFileZipper();
    }

    public static FileFileZipper getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    public void zipOrThrown(File zip, File value) throws IOException, InvalidPathException {
        PathFileZipper.getInstance()
                .zipOrThrown(zip.toPath(), value);
    }
}
