package com.github.trfiles.management.io.uploaders;

import com.github.trfiles.management.io.uploaders.fromPath.PathUploaderBuilder;
import com.github.trfiles.management.io.uploaders.fromStream.StreamUploaderBuilder;
import com.github.utilities.validators.Preconditions;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Public entry point of the fluent {@code Uploader} API, reachable via {@link Uploader#builder()}. Mirrors
 * {@link com.github.trfiles.management.io.downloaders.DownloaderBuilder}, but flipped: since a
 * {@code Downloader}'s source is always the same type ({@link java.net.URI}) and only its destination
 * varies, {@code DownloaderBuilder} picks the destination first; here it is the {@code Uploader}'s
 * destination that is always the same type ({@link java.net.URI}) while its source varies, so this
 * builder picks the source first ({@link #fromPath}/{@link #fromStream}), then the level-below builder
 * picks the destination (currently always a {@code URI}, via {@code toURI}/{@code toURL}).
 * <pre>{@code
 * Uploader.builder().fromPath(source).toURI(uri).upload();
 * Uploader.builder().fromStream(is).toURI(uri).upload();
 * }</pre>
 */
public class UploaderBuilder {
    private UploaderBuilder() {
    }

    private static final class Holder {
        private static final UploaderBuilder INSTANCE = new UploaderBuilder();
    }

    public static UploaderBuilder get() {
        return Holder.INSTANCE;
    }

    public StreamUploaderBuilder fromStream(InputStream is) {
        Preconditions.simpleParameterNotNull(is, "is");
        return new StreamUploaderBuilder(is);
    }

    public PathUploaderBuilder fromPath(Path path) {
        Preconditions.simpleParameterNotNull(path, "path");
        return new PathUploaderBuilder(path.toAbsolutePath());
    }

    public PathUploaderBuilder fromPath(File file) {
        Preconditions.simpleParameterNotNull(file, "file");
        return fromPath(file.toPath());
    }

    public PathUploaderBuilder fromPath(String path) {
        Preconditions.simpleParameterNotNull(path, "path");
        return fromPath(Paths.get(path));
    }
}
