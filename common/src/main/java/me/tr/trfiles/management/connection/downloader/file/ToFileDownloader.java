package me.tr.trfiles.management.connection.downloader.file;

import me.tr.trfiles.management.connection.downloader.Downloaders;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.InvalidPathException;

public class ToFileDownloader extends IFileDownloader {
    private ToFileDownloader() {
    }

    private record Holder() {
        private static final ToFileDownloader INSTANCE = new ToFileDownloader();
    }

    public static ToFileDownloader getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    public File downloadOrThrown(URL url, File destination) throws IOException, InvalidPathException {
        Downloaders.getPathDownloader()
                .downloadOrThrown(url, destination.toPath());
        return destination;
    }
}
