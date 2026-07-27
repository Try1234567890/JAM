package me.tr.trfiles.management.connection.downloader.stream;

import me.tr.trfiles.management.io.streaming.Streamings;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

public class ToStreamDownloader extends IStreamDownloader {
    private ToStreamDownloader() {
    }

    private record Holder() {
        private static final ToStreamDownloader INSTANCE = new ToStreamDownloader();
    }

    public static ToStreamDownloader getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    public OutputStream downloadOrThrown(URL url, OutputStream destination) throws IOException {
        try (InputStream in = url.openStream()) {
            Streamings.getStreamToStreamStreaming().writeOrThrown(in, destination);
        }
        return destination;
    }

}
