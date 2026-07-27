package me.tr.trfiles.management.archives.unzipper;

import com.github.utilities.validators.Preconditions;
import me.tr.trfiles.management.archives.unzipper.zip.file.ZIPFileParallelUnZipper;
import me.tr.trfiles.management.archives.unzipper.zip.file.ZIPFileUnZipper;
import me.tr.trfiles.management.archives.unzipper.zip.path.ZIPPathParallelUnZipper;
import me.tr.trfiles.management.archives.unzipper.zip.path.ZIPPathUnZipper;
import me.tr.trfiles.management.archives.unzipper.zis.ZISFileUnZipper;
import me.tr.trfiles.management.archives.unzipper.zis.ZISPathUnZipper;

import java.io.File;
import java.nio.file.Path;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

public class UnZippers {

    private UnZippers() {
    }

    public static InstanceUnZipper<ZipInputStream, File> newUnZipper(ZipInputStream zip, File file) {
        return new InstanceUnZipper<>(Preconditions.parameterNotNull(zip, "zip"),
                Preconditions.parameterNotNull(file, "file"), ZISFileUnZipper.getInstance());
    }

    public static InstanceUnZipper<ZipInputStream, Path> newUnZipper(ZipInputStream zip, Path path) {
        return new InstanceUnZipper<>(Preconditions.parameterNotNull(zip, "zip"),
                Preconditions.parameterNotNull(path, "path"), ZISPathUnZipper.getInstance());
    }

    public static InstanceUnZipper<ZipFile, File> newUnZipper(ZipFile zip, File file) {
        return new InstanceUnZipper<>(Preconditions.parameterNotNull(zip, "zip"),
                Preconditions.parameterNotNull(file, "file"), ZIPFileUnZipper.getInstance());
    }

    public static InstanceUnZipper<ZipFile, File> newParallelUnZipper(ZipFile zip, File file) {
        return new InstanceUnZipper<>(Preconditions.parameterNotNull(zip, "zip"),
                Preconditions.parameterNotNull(file, "file"), ZIPFileParallelUnZipper.getInstance());
    }

    public static InstanceUnZipper<ZipFile, Path> newUnZipper(ZipFile zip, Path file) {
        return new InstanceUnZipper<>(Preconditions.parameterNotNull(zip, "zip"),
                Preconditions.parameterNotNull(file, "file"), ZIPPathUnZipper.getInstance());
    }

    public static InstanceUnZipper<ZipFile, Path> newParallelUnZipper(ZipFile zip, Path file) {
        return new InstanceUnZipper<>(Preconditions.parameterNotNull(zip, "zip"),
                Preconditions.parameterNotNull(file, "file"), ZIPPathParallelUnZipper.getInstance());
    }

    public static ZISFileUnZipper getZISFileUnZipper() {
        return ZISFileUnZipper.getInstance();
    }
    public static ZISPathUnZipper getZISPathUnZipper() {
        return ZISPathUnZipper.getInstance();
    }
    public static ZIPFileUnZipper getZIPFileUnZipper() {
        return ZIPFileUnZipper.getInstance();
    }
    public static ZIPFileParallelUnZipper getZIPFileParallelUnZipper() {
        return ZIPFileParallelUnZipper.getInstance();
    }
    public static ZIPPathUnZipper getZIPPathUnZipper() {
        return ZIPPathUnZipper.getInstance();
    }
    public static ZIPPathParallelUnZipper getZIPPathParallelUnZipper() {
        return ZIPPathParallelUnZipper.getInstance();
    }
}
