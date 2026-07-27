package com.github.trfiles.management.connection.downloader.file;

import com.github.trfiles.Utility;
import com.github.trfiles.management.connection.downloader.Downloaders;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.InvalidPathException;

public class ToFileParallelDownloader extends IFileDownloader {

    private ToFileParallelDownloader() {
    }

    private record Holder() {
        private static final ToFileParallelDownloader INSTANCE = new ToFileParallelDownloader();
    }

    public static ToFileParallelDownloader getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    public File downloadOrThrown(URL url, File destination) throws IOException {
        return downloadOrThrown(url, destination, Utility.THREADS_AMOUNT);
    }

    public File downloadOrThrown(URL url, File destination, int threadCount) throws IOException, InvalidPathException {
        Downloaders.getParallelPathDownloader()
                .downloadOrThrown(url, destination.toPath(), threadCount);
        return destination;
    }
}