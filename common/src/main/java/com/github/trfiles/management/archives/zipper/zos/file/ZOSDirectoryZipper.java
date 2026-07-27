package com.github.trfiles.management.archives.zipper.zos.file;

import com.github.trfiles.management.archives.zipper.zos.ZOSZipper;

import java.io.File;
import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.util.zip.ZipOutputStream;

public class ZOSDirectoryZipper extends ZOSZipper<File> {

    private ZOSDirectoryZipper() {
    }

    private record Holder() {
        private static final ZOSDirectoryZipper INSTANCE = new ZOSDirectoryZipper();
    }

    public static ZOSDirectoryZipper getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    public void zipOrThrown(ZipOutputStream zip, File value) throws IOException, InvalidPathException {

    }

}
