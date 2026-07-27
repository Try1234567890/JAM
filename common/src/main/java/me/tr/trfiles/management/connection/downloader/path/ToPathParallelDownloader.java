package me.tr.trfiles.management.connection.downloader.path;

import me.tr.trfiles.Utility;
import me.tr.trfiles.management.FileCreator;
import me.tr.trfiles.management.io.streaming.Streamings;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ToPathParallelDownloader extends IPathDownloader {
    private static final ToPathDownloader DOWNLOADER = ToPathDownloader.getInstance();

    private ToPathParallelDownloader() {
    }

    private record Holder() {
        private static final ToPathParallelDownloader INSTANCE = new ToPathParallelDownloader();
    }

    public static ToPathParallelDownloader getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    public Path downloadOrThrown(URL url, Path destination) throws IOException {
        return downloadOrThrown(url, destination, Utility.THREADS_AMOUNT);
    }

    public Path downloadOrThrown(URL url, Path destination, int threadCount) throws IOException {
        long fileSize = getFileSize(url);

        if (fileSize <= 0 || threadCount <= 1) {
            return DOWNLOADER.downloadOrThrown(url, destination);
        }

        ExecutorService executor = Executors.newFixedThreadPool(threadCount);
        long chunkSize = fileSize / threadCount;
        CompletableFuture<?>[] futures = new CompletableFuture[threadCount];

        for (int i = 0; i < threadCount; i++) {
            long start = i * chunkSize;
            long end = (i == threadCount - 1) ? fileSize - 1 : (start + chunkSize - 1);

            Path partFile = Paths.get(destination.toString() + ".part" + i);
            futures[i] = CompletableFuture.runAsync(() -> {
                try {
                    downloadPart(url, partFile, start, end);
                } catch (IOException e) {
                    throw new RuntimeException("An error occurs while unzipping the part " + partFile.toAbsolutePath(), e);
                }
            }, executor);
        }

        CompletableFuture.allOf(futures).join();
        executor.shutdown();

        mergeFiles(destination, threadCount);
        return destination;
    }

    private void downloadPart(URL url, Path partFile, long start, long end) throws IOException {
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestProperty("Range", "bytes=" + start + "-" + end);

        try (InputStream in = conn.getInputStream()) {
            Streamings.getStreamToPathStreaming().writeOrThrown(in, partFile);
        }
    }

    private void mergeFiles(Path destination, int partCount) throws IOException {
        try (OutputStream out = Files.newOutputStream(destination)) {
            for (int i = 0; i < partCount; i++) {
                Path partFile = Paths.get(destination + ".part" + i);
                try (InputStream in = Files.newInputStream(partFile)) {
                    Streamings.getStreamToStreamStreaming().writeOrThrown(in, out);
                }

                Throwable throwable = FileCreator.delete(partFile).error();
                Validator.thrownIfNonNull(Validator.asIO(throwable));
            }
        }
    }

    private long getFileSize(URL url) {
        try {
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("HEAD");
            return conn.getContentLengthLong();
        } catch (IOException e) {
            return -1;
        }
    }
}