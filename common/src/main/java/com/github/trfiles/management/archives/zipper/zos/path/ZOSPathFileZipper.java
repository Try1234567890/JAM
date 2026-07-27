package com.github.trfiles.management.archives.zipper.zos.path;

import com.github.trfiles.management.archives.zipper.zos.ZOSZipper;
import com.github.trfiles.management.io.streaming.Streamings;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZOSPathFileZipper extends ZOSZipper<Path> {
    private static final ZOSPathDirectoryZipper FOLDER_ZIPPER = ZOSPathDirectoryZipper.getInstance();

    private ZOSPathFileZipper() {
    }

    private record Holder() {
        private static final ZOSPathFileZipper INSTANCE = new ZOSPathFileZipper();
    }

    public static ZOSPathFileZipper getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    public void zipOrThrown(ZipOutputStream zip, Path value) throws IOException {
        if (Files.isDirectory(value)) {
            FOLDER_ZIPPER.zipOrThrown(zip, value);
            return;
        }

        zipEntry(zip, value);
    }

    void zipEntry(ZipOutputStream zip, Path value) throws IOException {
        ZipEntry entry = new ZipEntry(value.toString());
        zip.putNextEntry(entry);
        Streamings.getPathToStreamStreaming().writeOrThrown(value, zip);
        zip.closeEntry();
    }
}
