package com.github.trfiles.management.io.zippers.toPath;

import com.github.trfiles.management.io.zippers.toZOS.PathCollectiontoZOSZipper;
import com.github.trfiles.management.io.zippers.toZOS.ZOSZipper;

import java.nio.file.Path;
import java.util.Collection;
import java.util.zip.ZipOutputStream;

public class PathCollectiontoPathZipper extends PathZipper<Collection<Path>> {
    private PathCollectiontoPathZipperConfiguration configuration;

    protected PathCollectiontoPathZipper(Collection<Path> input, Path destination, PathCollectiontoPathZipperConfiguration configuration) {
        super(input, destination);
        this.configuration = configuration != null ? configuration : new PathCollectiontoPathZipperConfiguration();
    }

    public static PathCollectiontoPathZipper newInstance(Collection<Path> input, Path destination, PathCollectiontoPathZipperConfiguration configuration) {
        return new PathCollectiontoPathZipper(input, destination, configuration);
    }

    public static PathCollectiontoPathZipper newInstance(Collection<Path> input, Path destination) {
        return new PathCollectiontoPathZipper(input, destination, new PathCollectiontoPathZipperConfiguration());
    }

    @Override
    public PathCollectiontoPathZipperConfiguration getConfiguration() {
        return configuration;
    }

    public PathCollectiontoPathZipper withConfiguration(PathCollectiontoPathZipperConfiguration configuration) {
        this.configuration = configuration;
        return this;
    }

    @Override
    protected ZOSZipper<Collection<Path>> toZOS(Collection<Path> input, ZipOutputStream destination) {
        return PathCollectiontoZOSZipper.newInstance(input, destination)
                .withConfiguration(getConfiguration().getZipperConfiguration());
    }

    public static class PathCollectiontoPathZipperConfiguration extends PathZipperConfiguration<Collection<Path>> {
        private PathCollectiontoZOSZipper.PathCollectiontoZOSZipperConfiguration zipperConfiguration = new PathCollectiontoZOSZipper.PathCollectiontoZOSZipperConfiguration();

        public PathCollectiontoZOSZipper.PathCollectiontoZOSZipperConfiguration getZipperConfiguration() {
            return zipperConfiguration != null ? zipperConfiguration : new PathCollectiontoZOSZipper.PathCollectiontoZOSZipperConfiguration();
        }

        public PathCollectiontoPathZipperConfiguration withZipperConfiguration(PathCollectiontoZOSZipper.PathCollectiontoZOSZipperConfiguration configuration) {
            this.zipperConfiguration = configuration;
            return this;
        }
    }
}
