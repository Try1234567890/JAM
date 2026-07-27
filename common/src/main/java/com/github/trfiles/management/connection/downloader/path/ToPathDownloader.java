package com.github.trfiles.management.connection.downloader.path;

import com.github.trfiles.management.io.streaming.Streamings;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Path;

public class ToPathDownloader extends IPathDownloader {
    private ToPathDownloader() {
    }

    private record Holder() {
        private static final ToPathDownloader INSTANCE = new ToPathDownloader();
    }

    public static ToPathDownloader getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    public Path downloadOrThrown(URL url, Path destination) throws IOException {
        try (InputStream in = url.openStream()) {
            Streamings.getStreamToPathStreaming().writeOrThrown(in, destination);
        }
        return destination;
    }

}
