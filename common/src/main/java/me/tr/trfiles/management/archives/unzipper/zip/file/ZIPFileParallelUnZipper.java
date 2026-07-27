package me.tr.trfiles.management.archives.unzipper.zip.file;

import me.tr.trfiles.management.archives.unzipper.zip.ZIPUnZipper;
import me.tr.trfiles.management.archives.unzipper.zip.path.ZIPPathParallelUnZipper;

import java.io.File;
import java.io.IOException;
import java.util.zip.ZipFile;

public class ZIPFileParallelUnZipper extends ZIPUnZipper<File> {
    private ZIPFileParallelUnZipper() {
    }

    private record Holder() {
        private static final ZIPFileParallelUnZipper INSTANCE = new ZIPFileParallelUnZipper();
    }

    public static ZIPFileParallelUnZipper getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    public File unzipOrThrown(ZipFile zip, File to) throws IOException {
        ZIPPathParallelUnZipper.getInstance()
                .unzipOrThrown(zip, to.toPath());
        return to;
    }
}
