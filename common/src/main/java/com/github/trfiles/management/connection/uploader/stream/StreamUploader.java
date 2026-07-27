package com.github.trfiles.management.connection.uploader.stream;

import com.github.trfiles.management.io.streaming.Streamings;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class StreamUploader extends IStreamUploader {
    private StreamUploader() {
    }

    private record Holder() {
        private static final StreamUploader INSTANCE = new StreamUploader();
    }

    public static StreamUploader getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    public void uploadOrThrown(URL url, InputStream source) throws IOException {
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setDoOutput(true);
        conn.setRequestMethod("POST");
        conn.setChunkedStreamingMode(16384);

        try (OutputStream out = conn.getOutputStream()) {
            Streamings.getStreamToStreamStreaming().writeOrThrown(source, out);
        }

        if (conn.getResponseCode() >= 400) {
            throw new IOException("HTTP upload failed with code " + conn.getResponseCode());
        }
    }
}