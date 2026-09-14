package com.github.trfiles.management.io.downloaders.toStream;

import com.github.utilities.validators.Preconditions;

import java.io.OutputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * Second-level builder reached via {@code Downloader.builder().toStream(os)}, picking the remote source to
 * download from.
 */
public class StreamDownloaderBuilder {
    private final OutputStream os;

    public StreamDownloaderBuilder(OutputStream os) {
        this.os = Preconditions.simpleParameterNotNull(os, "os");
    }

    public URItoStreamDownloader fromURI(URI uri) {
        Preconditions.simpleParameterNotNull(uri, "uri");
        return URItoStreamDownloader.newInstance(uri, os);
    }

    public URItoStreamDownloader fromURL(URL url) throws URISyntaxException {
        Preconditions.simpleParameterNotNull(url, "url");
        return fromURI(url.toURI());
    }

    public URItoStreamDownloader fromURI(String uri) {
        Preconditions.simpleParameterNotNull(uri, "uri");
        return fromURI(URI.create(uri));
    }
}
