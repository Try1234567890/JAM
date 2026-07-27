package com.github.trfiles.management.connection.uploader.path;

import com.github.trfiles.Utility;

import java.io.IOException;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PathParallelUploader extends IPathUploader {
    private static final PathUploader DEFAULT_UPLOADER = PathUploader.getInstance();
    private static final long MIN_PARALLEL_SIZE = 1024 * 1024;

    private PathParallelUploader() {
    }

    private record Holder() {
        private static final PathParallelUploader INSTANCE = new PathParallelUploader();
    }

    public static PathParallelUploader getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    public void uploadOrThrown(URL url, Path source) throws IOException {
        uploadOrThrown(url, source, Utility.THREADS_AMOUNT);
    }

    public void uploadOrThrown(URL url, Path source, int threadCount) throws IOException {
        long fileSize = Files.size(source);

        if (fileSize <= MIN_PARALLEL_SIZE || threadCount <= 1) {
            DEFAULT_UPLOADER.uploadOrThrown(url, source);
            return;
        }

        ExecutorService executor = Executors.newFixedThreadPool(threadCount);
        try {
            long chunkSize = (fileSize + threadCount - 1) / threadCount;
            CompletableFuture<?>[] futures = new CompletableFuture[threadCount];

            for (int i = 0; i < threadCount; i++) {
                final int index = i;
                long start = i * chunkSize;
                if (start >= fileSize) break;

                long end = Math.min(start + chunkSize - 1, fileSize - 1);

                futures[i] = CompletableFuture.runAsync(() -> {
                    try {
                        uploadPart(source, url, start, end, index);
                    } catch (IOException e) {
                        throw new CompletionException(e);
                    }
                }, executor);
            }

            CompletableFuture.allOf(futures).join();
        } catch (CompletionException e) {
            throw new IOException("An error occurs while uploading a file part", e.getCause());
        } finally {
            executor.shutdown();
        }
    }

    private void uploadPart(Path source, URL url, long start, long end, int index) throws IOException {
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setDoOutput(true);
        conn.setRequestMethod("POST");

        long length = end - start + 1;
        conn.setRequestProperty("Content-Range", "bytes " + start + "-" + end + "/" + Files.size(source));
        conn.setRequestProperty("Content-Type", "application/octet-stream");
        conn.setFixedLengthStreamingMode(length);
        conn.setConnectTimeout(10000);

        try (RandomAccessFile raf = new RandomAccessFile(source.toFile(), "r");
             OutputStream out = conn.getOutputStream()) {

            raf.seek(start);
            byte[] buffer = new byte[8192];
            long remaining = length;

            while (remaining > 0) {
                int toRead = (int) Math.min(buffer.length, remaining);
                int read = raf.read(buffer, 0, toRead);
                if (read == -1) break;
                out.write(buffer, 0, read);
                remaining -= read;
            }
        }

        if (conn.getResponseCode() >= 400) {
            throw new IOException("Part " + index + " failed: " + conn.getResponseCode());
        }
    }
}