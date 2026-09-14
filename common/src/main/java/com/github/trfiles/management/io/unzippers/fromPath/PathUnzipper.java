package com.github.trfiles.management.io.unzippers.fromPath;

import com.github.trfiles.management.FileUtility;
import com.github.trfiles.management.io.unzippers.InstanceUnzipper;
import com.github.trfiles.management.io.unzippers.UnzipperConfiguration;
import com.github.trfiles.management.io.unzippers.fromZIS.ZISUnzipper;
import com.github.trfiles.management.managers.FileManager;

import java.io.IOException;
import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * Base class for unzippers whose source is a ZIP file on disk, identified by
 * a {@link Path}. Mirrors {@code PathZipper}: verifies that {@code input} is
 * actually a valid ZIP file, opens a {@link ZipInputStream} on it, and
 * delegates the real extraction work to the corresponding {@code fromZIS}
 * unzipper.
 *
 * @param <D> the type of the destination.
 */
public abstract class PathUnzipper<D> extends InstanceUnzipper<Path, D> {
    protected PathUnzipper(Path input, D destination) {
        super(input, destination);
    }

    @Override
    protected void unzipEffective(Path input, D destination, Predicate<ZipEntry> include) throws Exception {
        if (!FileUtility.isZip(input)) throw new IOException("Source file is not a zip file: " + input);

        try (ZipInputStream zis = new ZipInputStream(FileManager.newInputStream(input))) {
            fromZIS(input, zis, destination).unzip(include);
        }
    }

    protected abstract ZISUnzipper<D> fromZIS(Path input, ZipInputStream zis, D destination);

    public static class PathUnzipperConfiguration<D> extends UnzipperConfiguration<Path, D> {

    }

}
