package me.tr.trfiles.management.archives.unzipper.zis;

import me.tr.trfiles.management.FileCreator;
import me.tr.trfiles.management.io.streaming.Streamings;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class ZISPathUnZipper extends ZISUnZipper<Path> {
    private ZISPathUnZipper() {
    }

    private record Holder() {
        private static final ZISPathUnZipper INSTANCE = new ZISPathUnZipper();
    }

    public static ZISPathUnZipper getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    public Path unzipOrThrown(ZipInputStream zip, Path to) throws IOException {
        if (!Files.isDirectory(to)) {
            throw new IllegalArgumentException("The destination " + to + " is not a directory");
        }

        ZipEntry entry;
        while ((entry = zip.getNextEntry()) != null) {
            String entryName = entry.getName();
            checkForZipSlip(to, Paths.get(entryName));
            Path out = Paths.get(to.toString(), entryName);

            if (entry.isDirectory()) {
                FileCreator.newDirectories(out);
            } else {
                FileCreator.newFile(out);
                Streamings.getStreamToPathStreaming().writeOrThrown(zip, out);
            }
        }

        return to;
    }
}
