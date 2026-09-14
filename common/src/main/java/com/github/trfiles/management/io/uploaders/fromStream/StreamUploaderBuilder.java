package com.github.trfiles.management.io.uploaders.fromStream;

import com.github.utilities.validators.Preconditions;

import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * Second-level builder reached via {@code Uploader.builder().fromStream(is)}, picking the remote
 * destination to upload to.
 */
public class StreamUploaderBuilder {
    private final InputStream is;

    public StreamUploaderBuilder(InputStream is) {
        this.is = Preconditions.simpleParameterNotNull(is, "is");
    }

    public StreamtoURIUploader toURI(URI uri) {
        Preconditions.simpleParameterNotNull(uri, "uri");
        return StreamtoURIUploader.newInstance(is, uri);
    }

    public StreamtoURIUploader toURL(URL url) throws URISyntaxException {
        Preconditions.simpleParameterNotNull(url, "url");
        return toURI(url.toURI());
    }

    public StreamtoURIUploader toURI(String uri) {
        Preconditions.simpleParameterNotNull(uri, "uri");
        return toURI(URI.create(uri));
    }
}
