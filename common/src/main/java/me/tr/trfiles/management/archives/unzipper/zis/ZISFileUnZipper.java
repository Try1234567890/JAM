package me.tr.trfiles.management.archives.unzipper.zis;

import java.io.File;
import java.io.IOException;
import java.util.zip.ZipInputStream;

public class ZISFileUnZipper extends ZISUnZipper<File> {
    private ZISFileUnZipper() {
    }

    private record Holder() {
        private static final ZISFileUnZipper INSTANCE = new ZISFileUnZipper();
    }

    public static ZISFileUnZipper getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    public File unzipOrThrown(ZipInputStream zip, File to) throws IOException {
        ZISPathUnZipper.getInstance()
                .unzipOrThrown(zip, to.toPath());
        return to;
    }
}
