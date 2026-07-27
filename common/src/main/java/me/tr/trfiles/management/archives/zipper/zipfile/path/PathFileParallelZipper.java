package me.tr.trfiles.management.archives.zipper.zipfile.path;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

public class PathFileParallelZipper extends IPathZipper<File> {

    private PathFileParallelZipper() {
    }

    private record Holder() {
        private static final PathFileParallelZipper INSTANCE = new PathFileParallelZipper();
    }

    public static PathFileParallelZipper getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    protected void zipOrThrown0(Path zip, File value) throws IOException {
        PathPathParallelZipper.getInstance()
                .zipOrThrown(zip, value.toPath());
    }
}
