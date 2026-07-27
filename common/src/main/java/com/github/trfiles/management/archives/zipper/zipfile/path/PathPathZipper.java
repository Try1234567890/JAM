package com.github.trfiles.management.archives.zipper.zipfile.path;

import com.github.trfiles.management.archives.zipper.zos.path.ZOSPathFileZipper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.zip.ZipOutputStream;

public class PathPathZipper extends IPathZipper<Path> {
    private static final ZOSPathFileZipper ZOS_ZIPPER = ZOSPathFileZipper.getInstance();

    private PathPathZipper() {
    }

    private record Holder() {
        private static final PathPathZipper INSTANCE = new PathPathZipper();
    }

    public static PathPathZipper getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    protected void zipOrThrown0(Path zip, Path value) throws IOException {
        try (ZipOutputStream zos = new ZipOutputStream(Files.newOutputStream(zip))) {
            ZOS_ZIPPER.zipOrThrown(zos, value);
        }
    }
}
