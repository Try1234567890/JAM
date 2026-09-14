package com.github.trfiles.management.io.downloaders.toPath;

import com.github.trfiles.management.io.downloaders.toStream.StreamDownloader;
import com.github.trfiles.management.io.downloaders.toStream.URItoStreamDownloader;

import java.io.OutputStream;
import java.net.URI;
import java.nio.file.Path;

/**
 * Downloads a remote {@link URI} directly onto a file on disk (a destination {@link Path}). This is the
 * class most consumers of this package are expected to use directly, e.g.:
 * <pre>{@code
 * URItoPathDownloader.newInstance(uri, destination).download();
 * }</pre>
 * or, reached from the fluent entry point:
 * <pre>{@code
 * Downloader.builder().toPath(destination).fromURI(uri).download();
 * }</pre>
 * Opens the destination file as an {@link OutputStream} (applying the configured {@code NewPathPolicy}
 * first) and delegates the actual network download to {@link URItoStreamDownloader}.
 */
public class URItoPathDownloader extends PathDownloader<URI> {
    private URItoPathDownloaderConfiguration configuration;

    private URItoPathDownloader(URI source, Path destination, URItoPathDownloaderConfiguration configuration) {
        super(source, destination);
        this.configuration = configuration != null ? configuration : new URItoPathDownloaderConfiguration();
    }

    public static URItoPathDownloader newInstance(URI source, Path destination) {
        return new URItoPathDownloader(source, destination, new URItoPathDownloaderConfiguration());
    }

    public static URItoPathDownloader newInstance(URI source, Path destination, URItoPathDownloaderConfiguration configuration) {
        return new URItoPathDownloader(source, destination, configuration);
    }

    @Override
    public URItoPathDownloaderConfiguration getConfiguration() {
        return configuration;
    }

    public URItoPathDownloader withConfiguration(URItoPathDownloaderConfiguration configuration) {
        this.configuration = configuration;
        return this;
    }

    @Override
    protected StreamDownloader<URI> toStreamDownloader(URI source, OutputStream os) {
        return URItoStreamDownloader.newInstance(source, os, getConfiguration().getStreamDownloaderConfiguration());
    }

    public static class URItoPathDownloaderConfiguration extends PathConfiguration<URI> {
        private URItoStreamDownloader.URIStreamDownloaderConfiguration streamDownloaderConfiguration =
                new URItoStreamDownloader.URIStreamDownloaderConfiguration();

        public URItoPathDownloaderConfiguration withStreamDownloaderConfiguration(
                URItoStreamDownloader.URIStreamDownloaderConfiguration configuration) {
            this.streamDownloaderConfiguration = configuration;
            return this;
        }

        public URItoStreamDownloader.URIStreamDownloaderConfiguration getStreamDownloaderConfiguration() {
            return streamDownloaderConfiguration != null
                    ? streamDownloaderConfiguration
                    : new URItoStreamDownloader.URIStreamDownloaderConfiguration();
        }
    }
}
