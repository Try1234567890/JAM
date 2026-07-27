package me.tr.trfiles.management.archives.unzipper.zip.path;

import me.tr.trfiles.management.FileCreator;
import me.tr.trfiles.management.archives.unzipper.zip.ZIPUnZipper;
import me.tr.trfiles.management.io.streaming.Streamings;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class ZIPPathParallelUnZipper extends ZIPUnZipper<Path> {
    private ZIPPathParallelUnZipper() {
    }

    private record Holder() {
        private static final ZIPPathParallelUnZipper INSTANCE = new ZIPPathParallelUnZipper();
    }

    public static ZIPPathParallelUnZipper getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    public Path unzipOrThrown(ZipFile zip, Path to) throws IOException {
        if (!Files.isDirectory(to)) {
            throw new IllegalArgumentException("The destination " + to + " is not a directory");
        }

        List<? extends ZipEntry> entries = Collections.list(zip.entries());
        ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

        List<CompletableFuture<Void>> futures = new ArrayList<>();

        for (ZipEntry entry : entries) {
            CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
                try {
                    processEntry(zip, entry, to);
                } catch (IOException e) {
                    throw new CompletionException(e);
                }
            }, executor);
            futures.add(future);
        }

        try {
            CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();
        } catch (CompletionException e) {
            if (e.getCause() instanceof IOException io) {
                throw io;
            }
            throw new IOException("Error during parallel extraction", e);
        } finally {
            executor.shutdown();
        }

        return to;
    }

    private void processEntry(ZipFile zip, ZipEntry entry, Path to) throws IOException {
        String entryName = entry.getName();
        Path out = Paths.get(to.toString(), entryName);
        checkForZipSlip(to, out);

        if (entry.isDirectory()) {
            FileCreator.newDirectories(out);
        } else {
            try (InputStream in = zip.getInputStream(entry)) {
                FileCreator.newFile(out);
                Streamings.getStreamToPathStreaming().writeOrThrown(in, out);
            }
        }
    }
}
