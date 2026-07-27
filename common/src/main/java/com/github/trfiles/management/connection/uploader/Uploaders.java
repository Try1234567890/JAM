package com.github.trfiles.management.connection.uploader;

import com.github.utilities.validators.Preconditions;
import com.github.trfiles.management.connection.uploader.path.PathUploader;
import com.github.trfiles.management.connection.uploader.path.PathParallelUploader;
import com.github.trfiles.management.connection.uploader.stream.ParallelStreamUploader;
import com.github.trfiles.management.connection.uploader.stream.StreamUploader;

import java.io.InputStream;
import java.net.URL;
import java.nio.file.Path;

public class Uploaders {

    private Uploaders() {
    }

    public static InstanceUploader<Path> newUploader(URL url, Path destination) {
        return new InstanceUploader<>(Preconditions.parameterNotNull(url, "url"),
                Preconditions.parameterNotNull(destination, "destination"), PathUploader.getInstance());
    }

    public static InstanceUploader<Path> newParallelUploader(URL url, Path destination) {
        return new InstanceUploader<>(Preconditions.parameterNotNull(url, "url"),
                Preconditions.parameterNotNull(destination, "destination"), PathParallelUploader.getInstance());
    }

    public static InstanceUploader<InputStream> newUploader(URL url, InputStream destination) {
        return new InstanceUploader<>(Preconditions.parameterNotNull(url, "url"),
                Preconditions.parameterNotNull(destination, "destination"), StreamUploader.getInstance());
    }

    public static InstanceUploader<InputStream> newParallelUploader(URL url, InputStream destination) {
        return new InstanceUploader<>(Preconditions.parameterNotNull(url, "url"),
                Preconditions.parameterNotNull(destination, "destination"), ParallelStreamUploader.getInstance());
    }

    public static PathUploader getPathUploader() {
        return PathUploader.getInstance();
    }

    public static PathParallelUploader getParallelPathUploader() {
        return PathParallelUploader.getInstance();
    }

    public static StreamUploader getStreamUploader() {
        return StreamUploader.getInstance();
    }

    public static ParallelStreamUploader getParallelStreamUploader() {
        return ParallelStreamUploader.getInstance();
    }
}
