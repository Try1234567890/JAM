package me.tr.trfiles.management.archives.unzipper.zip.path;

import me.tr.trfiles.management.FileCreator;
import me.tr.trfiles.management.archives.unzipper.zip.ZIPUnZipper;
import me.tr.trfiles.management.io.streaming.Streamings;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class ZIPPathUnZipper extends ZIPUnZipper<Path> {
    private ZIPPathUnZipper() {
    }

    private record Holder() {
        private static final ZIPPathUnZipper INSTANCE = new ZIPPathUnZipper();
    }

    public static ZIPPathUnZipper getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    public Path unzipOrThrown(ZipFile zip, Path to) throws IOException {
        if (!Files.isDirectory(to)) {
            throw new IllegalArgumentException("The destination " + to + " is not a directory");
        }

        Enumeration<? extends ZipEntry> entries = zip.entries();
        while (entries.hasMoreElements()) {
            ZipEntry entry = entries.nextElement();
            String entryName = entry.getName();
            checkForZipSlip(to, Paths.get(entryName));

            Path out = Paths.get(to.toString(), entryName);

            if (entry.isDirectory()) {
                FileCreator.newDirectories(out);
            } else {
                try (InputStream in = zip.getInputStream(entry)) {
                    FileCreator.newFile(out);
                    Streamings.getStreamToPathStreaming().writeOrThrown(in, out);
                }
            }
        }

        return to;
    }
}
