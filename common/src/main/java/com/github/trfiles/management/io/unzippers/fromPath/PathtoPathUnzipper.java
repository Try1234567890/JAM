package com.github.trfiles.management.io.unzippers.fromPath;

import com.github.trfiles.management.io.unzippers.fromZIS.ZISUnzipper;
import com.github.trfiles.management.io.unzippers.fromZIS.ZIStoPathUnzipper;

import java.nio.file.Path;
import java.util.zip.ZipInputStream;

/**
 * Extracts a ZIP file (a {@link Path} on disk) into a destination directory
 * (another {@link Path}). This is the class most consumers of this package
 * are expected to use directly, e.g.:
 * <pre>{@code
 * PathtoPathUnzipper.newInstance(zipFile, destinationDir).unzip();
 * }</pre>
 * Consistently with {@code zippers} (see {@code 01-architecture.md} /
 * {@code 03-extending-the-io-pipeline.md}), there is no central
 * {@code Unzipper.builder()}: concrete classes are instantiated directly via
 * {@code newInstance(...)}.
 */
public class PathtoPathUnzipper extends PathUnzipper<Path> {
    private PathtoPathUnzipperConfiguration configuration;

    protected PathtoPathUnzipper(Path input, Path destination, PathtoPathUnzipperConfiguration configuration) {
        super(input, destination);
        this.configuration = configuration != null ? configuration : new PathtoPathUnzipperConfiguration();
    }

    public static PathtoPathUnzipper newInstance(Path input, Path destination) {
        return new PathtoPathUnzipper(input, destination, new PathtoPathUnzipperConfiguration());
    }

    public static PathtoPathUnzipper newInstance(Path input, Path destination, PathtoPathUnzipperConfiguration configuration) {
        return new PathtoPathUnzipper(input, destination, configuration);
    }

    @Override
    public PathtoPathUnzipperConfiguration getConfiguration() {
        return configuration;
    }

    public PathtoPathUnzipper withConfiguration(PathtoPathUnzipperConfiguration configuration) {
        this.configuration = configuration;
        return this;
    }

    @Override
    protected ZISUnzipper<Path> fromZIS(Path input, ZipInputStream zis, Path destination) {
        return ZIStoPathUnzipper.newInstance(zis, destination)
                .withConfiguration(getConfiguration().getZISUnzipperConfiguration());
    }

    public static class PathtoPathUnzipperConfiguration extends PathUnzipperConfiguration<Path> {
        private ZIStoPathUnzipper.ZIStoPathUnzipperConfiguration zisUnzipperConfiguration = new ZIStoPathUnzipper.ZIStoPathUnzipperConfiguration();

        public ZIStoPathUnzipper.ZIStoPathUnzipperConfiguration getZISUnzipperConfiguration() {
            return zisUnzipperConfiguration != null ? zisUnzipperConfiguration : new ZIStoPathUnzipper.ZIStoPathUnzipperConfiguration();
        }

        public PathtoPathUnzipperConfiguration withZISUnzipperConfiguration(ZIStoPathUnzipper.ZIStoPathUnzipperConfiguration configuration) {
            this.zisUnzipperConfiguration = configuration;
            return this;
        }
    }
}
