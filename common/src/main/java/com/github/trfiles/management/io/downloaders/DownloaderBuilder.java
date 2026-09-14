package com.github.trfiles.management.io.downloaders;

import com.github.trfiles.management.io.downloaders.toPath.PathDownloaderBuilder;
import com.github.trfiles.management.io.downloaders.toStream.StreamDownloaderBuilder;
import com.github.utilities.validators.Preconditions;

import java.io.File;
import java.io.OutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Public entry point of the fluent {@code Downloader} API, reachable via {@link Downloader#builder()},
 * mirroring {@code StreamerBuilder}: picks the destination first ({@link #toPath}/{@link #toStream}), then
 * the level-below builder picks the source (currently always a {@link java.net.URI}, via {@code fromURI}/
 * {@code fromURL}).
 * <pre>{@code
 * Downloader.builder().toPath(dest).fromURI(uri).download();
 * Downloader.builder().toStream(os).fromURI(uri).download();
 * }</pre>
 */
public class DownloaderBuilder {
    private DownloaderBuilder() {
    }

    private static final class Holder {
        private static final DownloaderBuilder INSTANCE = new DownloaderBuilder();
    }

    public static DownloaderBuilder get() {
        return Holder.INSTANCE;
    }

    public StreamDownloaderBuilder toStream(OutputStream os) {
        Preconditions.simpleParameterNotNull(os, "os");
        return new StreamDownloaderBuilder(os);
    }

    public PathDownloaderBuilder toPath(Path path) {
        Preconditions.simpleParameterNotNull(path, "path");
        return new PathDownloaderBuilder(path.toAbsolutePath());
    }

    public PathDownloaderBuilder toPath(File file) {
        Preconditions.simpleParameterNotNull(file, "file");
        return toPath(file.toPath());
    }

    public PathDownloaderBuilder toPath(String path) {
        Preconditions.simpleParameterNotNull(path, "path");
        return toPath(Paths.get(path));
    }
}
