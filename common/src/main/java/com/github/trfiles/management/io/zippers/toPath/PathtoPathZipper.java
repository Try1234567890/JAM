package com.github.trfiles.management.io.zippers.toPath;

import com.github.trfiles.management.io.zippers.toZOS.PathtoZOSZipper;
import com.github.trfiles.management.io.zippers.toZOS.ZOSZipper;

import java.nio.file.Path;
import java.util.zip.ZipOutputStream;

public class PathtoPathZipper extends PathZipper<Path> {
    private PathtoPathZipperConfiguration configuration;

    protected PathtoPathZipper(Path input, Path destination, PathtoPathZipperConfiguration configuration) {
        super(input, destination);
        this.configuration = configuration != null ? configuration : new PathtoPathZipperConfiguration();
    }

    public static PathtoPathZipper newInstance(Path input, Path destination, PathtoPathZipperConfiguration configuration) {
        return new PathtoPathZipper(input, destination, configuration);
    }

    public static PathtoPathZipper newInstance(Path input, Path destination) {
        return new PathtoPathZipper(input, destination, new PathtoPathZipperConfiguration());
    }

    @Override
    public PathtoPathZipperConfiguration getConfiguration() {
        return configuration;
    }

    public PathtoPathZipper withConfiguration(PathtoPathZipperConfiguration configuration) {
        this.configuration = configuration;
        return this;
    }

    @Override
    protected ZOSZipper<Path> toZOS(Path input, ZipOutputStream destination) {
        return PathtoZOSZipper.newInstance(input, destination)
                .withConfiguration(getConfiguration().getZipperConfiguration());
    }

    public static class PathtoPathZipperConfiguration extends PathZipperConfiguration<Path> {
        private PathtoZOSZipper.PathtoZOSZipperConfiguration zipperConfiguration = new PathtoZOSZipper.PathtoZOSZipperConfiguration();

        public PathtoZOSZipper.PathtoZOSZipperConfiguration getZipperConfiguration() {
            return zipperConfiguration != null ? zipperConfiguration : new PathtoZOSZipper.PathtoZOSZipperConfiguration();
        }

        public PathtoPathZipperConfiguration withZipperConfiguration(PathtoZOSZipper.PathtoZOSZipperConfiguration configuration) {
            this.zipperConfiguration = configuration;
            return this;
        }
    }
}
