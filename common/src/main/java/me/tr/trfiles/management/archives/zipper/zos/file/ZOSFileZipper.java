package me.tr.trfiles.management.archives.zipper.zos.file;

import me.tr.trfiles.management.archives.zipper.zos.ZOSZipper;

import java.io.File;
import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.util.zip.ZipOutputStream;

public class ZOSFileZipper extends ZOSZipper<File> {

    private ZOSFileZipper() {
    }

    private record Holder() {
        private static final ZOSFileZipper INSTANCE = new ZOSFileZipper();
    }

    public static ZOSFileZipper getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    public void zipOrThrown(ZipOutputStream zip, File value) throws IOException, InvalidPathException {

    }
}
