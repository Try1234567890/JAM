package com.github.trfiles.management.io.uploaders.fromPath;

import com.github.trfiles.management.io.uploaders.fromStream.StreamUploader;
import com.github.trfiles.management.io.uploaders.fromStream.StreamtoURIUploader;

import java.io.InputStream;
import java.net.URI;
import java.nio.file.Path;

/**
 * Uploads a file on disk (a source {@link Path}) directly to a remote {@link URI}. This is the class most
 * consumers of this package are expected to use directly, e.g.:
 * <pre>{@code
 * PathtoURIUploader.newInstance(source, uri).upload();
 * }</pre>
 * or, reached from the fluent entry point:
 * <pre>{@code
 * Uploader.builder().fromPath(source).toURI(uri).upload();
 * }</pre>
 * Opens the source file as an {@link InputStream} and delegates the actual network upload to
 * {@link StreamtoURIUploader}.
 */
public class PathtoURIUploader extends PathUploader<URI> {
    private PathtoURIUploaderConfiguration configuration;

    private PathtoURIUploader(Path source, URI destination, PathtoURIUploaderConfiguration configuration) {
        super(source, destination);
        this.configuration = configuration != null ? configuration : new PathtoURIUploaderConfiguration();
    }

    public static PathtoURIUploader newInstance(Path source, URI destination) {
        return new PathtoURIUploader(source, destination, new PathtoURIUploaderConfiguration());
    }

    public static PathtoURIUploader newInstance(Path source, URI destination, PathtoURIUploaderConfiguration configuration) {
        return new PathtoURIUploader(source, destination, configuration);
    }

    @Override
    public PathtoURIUploaderConfiguration getConfiguration() {
        return configuration;
    }

    public PathtoURIUploader withConfiguration(PathtoURIUploaderConfiguration configuration) {
        this.configuration = configuration;
        return this;
    }

    @Override
    protected StreamUploader<URI> toStreamUploader(InputStream is, URI destination) {
        return StreamtoURIUploader.newInstance(is, destination, getConfiguration().getStreamUploaderConfiguration());
    }

    public static class PathtoURIUploaderConfiguration extends PathConfiguration<URI> {
        private StreamtoURIUploader.URIStreamUploaderConfiguration streamUploaderConfiguration =
                new StreamtoURIUploader.URIStreamUploaderConfiguration();

        public PathtoURIUploaderConfiguration withStreamUploaderConfiguration(
                StreamtoURIUploader.URIStreamUploaderConfiguration configuration) {
            this.streamUploaderConfiguration = configuration;
            return this;
        }

        public StreamtoURIUploader.URIStreamUploaderConfiguration getStreamUploaderConfiguration() {
            return streamUploaderConfiguration != null
                    ? streamUploaderConfiguration
                    : new StreamtoURIUploader.URIStreamUploaderConfiguration();
        }
    }
}
