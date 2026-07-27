package com.github.trfiles.management.connection.uploader.path;

import com.github.trfiles.management.io.streaming.Streamings;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;

public class PathUploader extends IPathUploader {
    private PathUploader() {
    }

    private record Holder() {
        private static final PathUploader INSTANCE = new PathUploader();
    }

    public static PathUploader getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    public void uploadOrThrown(URL url, Path source) throws IOException {
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setDoOutput(true);
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/octet-stream");
        conn.setFixedLengthStreamingMode(Files.size(source));

        try (OutputStream out = conn.getOutputStream()) {
            Streamings.getPathToStreamStreaming().writeOrThrown(source, out);
        }

        int responseCode = conn.getResponseCode();
        if (responseCode >= 400) {
            throw new IOException("HTTP upload failed with code: " + responseCode);
        }
    }
}