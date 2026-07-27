package me.tr.trfiles.management.archives.zipper.zipfile.path;

import java.io.File;
import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;

public class PathFileZipper extends IPathZipper<File> {

    private PathFileZipper() {
    }

    private record Holder() {
        private static final PathFileZipper INSTANCE = new PathFileZipper();
    }

    public static PathFileZipper getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    protected void zipOrThrown0(Path zip, File value) throws IOException, InvalidPathException {
        PathPathZipper.getInstance()
                .zipOrThrown(zip, value.toPath());
    }
}
