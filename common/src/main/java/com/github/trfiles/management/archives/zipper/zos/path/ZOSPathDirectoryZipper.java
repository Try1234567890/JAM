package com.github.trfiles.management.archives.zipper.zos.path;

import com.github.trfiles.management.archives.zipper.zos.ZOSZipper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZOSPathDirectoryZipper extends ZOSZipper<Path> {
    private static final ZOSPathFileZipper FILE_ZIPPER = ZOSPathFileZipper.getInstance();

    private ZOSPathDirectoryZipper() {
    }

    private record Holder() {
        private static final ZOSPathDirectoryZipper INSTANCE = new ZOSPathDirectoryZipper();
    }

    public static ZOSPathDirectoryZipper getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    public void zipOrThrown(ZipOutputStream zip, Path value) throws IOException {
        if (Files.isRegularFile(value)) {
            FILE_ZIPPER.zipOrThrown(zip, value);
            return;
        }

        try (Stream<Path> stream = Files.walk(value)) {
            stream.forEach(path -> {
                String name = path.toString();

                try {
                    if (Files.isDirectory(path)) {
                        zip.putNextEntry(new ZipEntry(name + "/"));
                        zip.closeEntry();
                    } else {
                        FILE_ZIPPER.zipEntry(zip, path);
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        }
    }
}
