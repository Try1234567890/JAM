package com.github.trfiles.management.io.uploaders.fromPath;

import com.github.utilities.validators.Preconditions;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;

/**
 * Second-level builder reached via {@code Uploader.builder().fromPath(source)}, picking the remote
 * destination to upload to.
 */
public class PathUploaderBuilder {
    private final Path path;

    public PathUploaderBuilder(Path path) {
        this.path = Preconditions.simpleParameterNotNull(path, "path");
    }

    public PathtoURIUploader toURI(URI uri) {
        Preconditions.simpleParameterNotNull(uri, "uri");
        return PathtoURIUploader.newInstance(path, uri);
    }

    public PathtoURIUploader toURL(URL url) throws URISyntaxException {
        Preconditions.simpleParameterNotNull(url, "url");
        return toURI(url.toURI());
    }

    public PathtoURIUploader toURI(String uri) {
        Preconditions.simpleParameterNotNull(uri, "uri");
        return toURI(URI.create(uri));
    }
}
