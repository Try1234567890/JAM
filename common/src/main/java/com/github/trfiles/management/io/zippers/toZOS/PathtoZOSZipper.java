package com.github.trfiles.management.io.zippers.toZOS;

import com.github.trfiles.management.managers.FileManager;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.zip.ZipOutputStream;

public class PathtoZOSZipper extends ZOSZipper<Path> {
    private PathtoZOSZipperConfiguration configuration;

    protected PathtoZOSZipper(Path input, ZipOutputStream destination, PathtoZOSZipperConfiguration configuration) {
        super(input, destination);
        this.configuration = configuration != null ? configuration : new PathtoZOSZipperConfiguration();
    }

    public static PathtoZOSZipper newInstance(Path input, ZipOutputStream destination, PathtoZOSZipperConfiguration configuration) {
        return new PathtoZOSZipper(input, destination, configuration);
    }

    public static PathtoZOSZipper newInstance(Path input, ZipOutputStream destination) {
        return new PathtoZOSZipper(input, destination, new PathtoZOSZipperConfiguration());
    }

    @Override
    public PathtoZOSZipperConfiguration getConfiguration() {
        return configuration;
    }

    public PathtoZOSZipper withConfiguration(PathtoZOSZipperConfiguration configuration) {
        this.configuration = configuration;
        return this;
    }

    @Override
    protected void zipEffective(Path input, ZipOutputStream destination, Predicate<Path> include) throws Exception {
        PathCollectiontoZOSZipper.newInstance(FileManager.list(input), destination)
                .withConfiguration(getConfiguration().getPathCollectionZipperConfiguration())
                .zip();
    }

    public static class PathtoZOSZipperConfiguration extends ZOSZipper.ZOSZipperConfiguration<Path> {
        private PathCollectiontoZOSZipper.PathCollectiontoZOSZipperConfiguration pathCollectionZipperConfiguration = new PathCollectiontoZOSZipper.PathCollectiontoZOSZipperConfiguration();

        public PathCollectiontoZOSZipper.PathCollectiontoZOSZipperConfiguration getPathCollectionZipperConfiguration() {
            return pathCollectionZipperConfiguration != null ? pathCollectionZipperConfiguration : new PathCollectiontoZOSZipper.PathCollectiontoZOSZipperConfiguration();
        }

        public PathtoZOSZipperConfiguration withPathCollectionZipperConfiguration(PathCollectiontoZOSZipper.PathCollectiontoZOSZipperConfiguration configuration) {
            this.pathCollectionZipperConfiguration = configuration;
            return this;
        }
    }
}
