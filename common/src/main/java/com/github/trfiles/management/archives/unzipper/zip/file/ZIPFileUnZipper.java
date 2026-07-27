package com.github.trfiles.management.archives.unzipper.zip.file;

import com.github.trfiles.management.archives.unzipper.zip.ZIPUnZipper;
import com.github.trfiles.management.archives.unzipper.zip.path.ZIPPathUnZipper;

import java.io.File;
import java.io.IOException;
import java.util.zip.ZipFile;

public class ZIPFileUnZipper extends ZIPUnZipper<File> {
    private ZIPFileUnZipper() {
    }

    private record Holder() {
        private static final ZIPFileUnZipper INSTANCE = new ZIPFileUnZipper();
    }

    public static ZIPFileUnZipper getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    public File unzipOrThrown(ZipFile zip, File to) throws IOException {
        ZIPPathUnZipper.getInstance()
                .unzipOrThrown(zip, to.toPath());
        return to;
    }
}
