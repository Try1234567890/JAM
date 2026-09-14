package com.github.trfiles.management.io.downloaders.toPath;

import com.github.utilities.validators.Preconditions;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;

/**
 * Second-level builder reached via {@code Downloader.builder().toPath(destination)}, picking the remote
 * source to download from.
 */
public class PathDownloaderBuilder {
    private final Path path;

    public PathDownloaderBuilder(Path path) {
        this.path = Preconditions.simpleParameterNotNull(path, "path");
    }

    public URItoPathDownloader fromURI(URI uri) {
        Preconditions.simpleParameterNotNull(uri, "uri");
        return URItoPathDownloader.newInstance(uri, path);
    }

    public URItoPathDownloader fromURL(URL url) throws URISyntaxException {
        Preconditions.simpleParameterNotNull(url, "url");
        return fromURI(url.toURI());
    }

    public URItoPathDownloader fromURI(String uri) {
        Preconditions.simpleParameterNotNull(uri, "uri");
        return fromURI(URI.create(uri));
    }
}
