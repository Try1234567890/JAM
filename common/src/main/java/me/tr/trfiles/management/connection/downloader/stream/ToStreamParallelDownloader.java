package me.tr.trfiles.management.connection.downloader.stream;

import me.tr.trfiles.Utility;
import me.tr.trfiles.management.io.streaming.Streamings;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.TreeMap;
import java.util.concurrent.*;

public class ToStreamParallelDownloader extends IStreamDownloader {
    private static final ToStreamDownloader DOWNLOADER = ToStreamDownloader.getInstance();

    private ToStreamParallelDownloader() {
    }

    private record Holder() {
        private static final ToStreamParallelDownloader INSTANCE = new ToStreamParallelDownloader();
    }

    public static ToStreamParallelDownloader getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    public OutputStream downloadOrThrown(URL url, OutputStream destination) throws IOException {
        return downloadOrThrown(url, destination, Utility.THREADS_AMOUNT);
    }

    public OutputStream downloadOrThrown(URL url, OutputStream destination, int threadCount) throws IOException {
        long fileSize = getFileSize(url);

        if (fileSize <= 0 || threadCount <= 1) {
            return DOWNLOADER.downloadOrThrown(url, destination);
        }

        long chunkSize = fileSize / threadCount;
        ExecutorService executor = Executors.newFixedThreadPool(threadCount);

        CompletionService<DownloadedPart> completionService = new ExecutorCompletionService<>(executor);

        for (int i = 0; i < threadCount; i++) {
            final int index = i;
            long start = i * chunkSize;
            long end = (i == threadCount - 1) ? fileSize - 1 : (start + chunkSize - 1);

            completionService.submit(() -> {
                byte[] data = downloadPartToMemory(url, start, end);
                return new DownloadedPart(index, data);
            });
        }

        TreeMap<Integer, byte[]> results = new TreeMap<>();
        int nextToRead = 0;

        try {
            for (int i = 0; i < threadCount; i++) {
                DownloadedPart part = completionService.take().get();
                results.put(part.index, part.data);

                while (results.containsKey(nextToRead)) {
                    destination.write(results.remove(nextToRead));
                    nextToRead++;
                }
            }
            destination.flush();
        } catch (InterruptedException | ExecutionException e) {
            throw new IOException("An error occurs while downloading a part of " + url, e);
        } finally {
            executor.shutdown();
        }

        return destination;
    }

    private byte[] downloadPartToMemory(URL url, long start, long end) throws IOException {
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestProperty("Range", "bytes=" + start + "-" + end);

        try (InputStream in = conn.getInputStream();
             ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
            Streamings.getStreamToStreamStreaming().writeOrThrown(in, bos);
            return bos.toByteArray();
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

    private record DownloadedPart(int index, byte[] data) {
    }
}
