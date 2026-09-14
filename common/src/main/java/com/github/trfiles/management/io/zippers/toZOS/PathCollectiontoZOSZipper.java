package com.github.trfiles.management.io.zippers.toZOS;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Collection;
import java.util.function.Predicate;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class PathCollectiontoZOSZipper extends ZOSZipper<Collection<Path>> {
    private PathCollectiontoZOSZipperConfiguration configuration;

    protected PathCollectiontoZOSZipper(Collection<Path> input, ZipOutputStream destination, PathCollectiontoZOSZipperConfiguration configuration) {
        super(input, destination);
        this.configuration = configuration != null ? configuration : new PathCollectiontoZOSZipperConfiguration();
    }

    public static PathCollectiontoZOSZipper newInstance(Collection<Path> input, ZipOutputStream destination, PathCollectiontoZOSZipperConfiguration configuration) {
        return new PathCollectiontoZOSZipper(input, destination, configuration);
    }

    public static PathCollectiontoZOSZipper newInstance(Collection<Path> input, ZipOutputStream destination) {
        return new PathCollectiontoZOSZipper(input, destination, new PathCollectiontoZOSZipperConfiguration());
    }

    @Override
    public PathCollectiontoZOSZipperConfiguration getConfiguration() {
        return configuration;
    }

    public PathCollectiontoZOSZipper withConfiguration(PathCollectiontoZOSZipperConfiguration configuration) {
        this.configuration = configuration;
        return this;
    }

    @Override
    protected void zipEffective(Collection<Path> input, ZipOutputStream destination, Predicate<Path> include) throws Exception {
        for (Path path : input) {
            if (!include.test(path)) continue;
            zipEntry(path, destination);
        }
    }

    private void zipEntry(Path input, ZipOutputStream dest) throws IOException {
        ZipEntry entry = new ZipEntry(input.toString());
        entry.setTime(input.toFile().lastModified());
        entry.setSize(input.toFile().length());
        entry.setMethod(ZipEntry.DEFLATED);
        dest.putNextEntry(entry);
    }

    public static class PathCollectiontoZOSZipperConfiguration extends ZOSZipperConfiguration<Collection<Path>> {

    }
}
