package com.github.trfiles.management.io.uploaders.fromPath;

import com.github.trfiles.management.io.uploaders.InstanceUploader;
import com.github.trfiles.management.io.uploaders.UploaderConfiguration;
import com.github.trfiles.management.io.uploaders.fromStream.StreamUploader;
import com.github.trfiles.management.managers.FileManager;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Base class for uploaders whose source is a file on disk, identified by a {@link Path}. Mirrors
 * {@code PathDownloader}: opens {@code source} as an {@link InputStream} and delegates the actual upload
 * to the corresponding {@code fromStream} uploader.
 *
 * @param <D> the destination of the process (a {@link java.net.URI} in every implementation currently in
 *            this package).
 */
public abstract class PathUploader<D> extends InstanceUploader<Path, D> {

    protected PathUploader(Path source, D destination) {
        super(source, destination);
    }

    @Override
    public abstract PathConfiguration<D> getConfiguration();

    /**
     * {@inheritDoc}
     * <p>
     * Delegates to {@link Files#size(Path)}: unlike an {@link InputStream} source (see
     * {@code StreamUploader#_size}), a local file's size IS known upfront without consuming it.
     */
    @Override
    protected long _size(Path value) throws Exception {
        return Files.size(value);
    }

    @Override
    protected void uploadEffectively(Path source, D destination, long offset, long length) throws Exception {
        checkReadable(source);

        try (InputStream is = FileManager.newInputStream(source)) {
            toStreamUploader(is, destination).upload(offset, length);
        }
    }

    protected abstract StreamUploader<D> toStreamUploader(InputStream is, D destination);

    public abstract static class PathConfiguration<D> extends UploaderConfiguration<Path, D> {
    }
}
