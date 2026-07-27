package me.tr.trfiles.management.connection.downloader;

import com.github.utilities.validators.Preconditions;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;

public class InstanceDownloader<D> {
    private final URL url;
    private final D destination;
    private final Downloader<D> downloader;

    protected InstanceDownloader(URL url, D destination, Downloader<D> downloader) {
        this.url = Preconditions.parameterNotNull(url, "url");
        this.destination = Preconditions.parameterNotNull(destination, "destination");
        this.downloader = Preconditions.parameterNotNull(downloader, "downloader");
    }

    public URL getUrl() {
        return url;
    }

    public D getDestination() {
        return destination;
    }

    public Downloader<D> getDownloader() {
        return downloader;
    }

    public D downloadOrThrown() throws IOException {
        return downloader.downloadOrThrown(url, destination);
    }

    public Optional<D> download() {
        return downloader.download(url, destination);
    }

    public D downloadOrNull() {
        return downloader.downloadOrNull(url, destination);
    }
}
