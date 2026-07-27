package com.github.trfiles.management.archives.zipper;

import com.github.utilities.validators.Preconditions;
import com.github.trfiles.management.archives.zipper.zipfile.file.FileFileParallelZipper;
import com.github.trfiles.management.archives.zipper.zipfile.file.FileFileZipper;
import com.github.trfiles.management.archives.zipper.zipfile.file.FilePathParallelZipper;
import com.github.trfiles.management.archives.zipper.zipfile.file.FilePathZipper;
import com.github.trfiles.management.archives.zipper.zipfile.path.PathFileParallelZipper;
import com.github.trfiles.management.archives.zipper.zipfile.path.PathFileZipper;
import com.github.trfiles.management.archives.zipper.zipfile.path.PathPathParallelZipper;
import com.github.trfiles.management.archives.zipper.zipfile.path.PathPathZipper;
import com.github.trfiles.management.archives.zipper.zos.file.ZOSDirectoryZipper;
import com.github.trfiles.management.archives.zipper.zos.file.ZOSFileZipper;
import com.github.trfiles.management.archives.zipper.zos.path.ZOSPathDirectoryZipper;
import com.github.trfiles.management.archives.zipper.zos.path.ZOSPathFileZipper;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.zip.ZipOutputStream;

public class Zippers {

    private Zippers() {
    }

    public static InstanceZipper<ZipOutputStream, File> newZipper(ZipOutputStream zip, File file) {
        Preconditions.parameterNotNull(zip, "zip");
        Preconditions.parameterNotNull(file, "file");

        return file.isFile()
                ? new InstanceZipper<>(zip, file, ZOSFileZipper.getInstance())
                : new InstanceZipper<>(zip, file, ZOSDirectoryZipper.getInstance());
    }

    public static InstanceZipper<ZipOutputStream, Path> newZipper(ZipOutputStream zip, Path path) {
        Preconditions.parameterNotNull(zip, "zip");
        Preconditions.parameterNotNull(path, "path");

        return Files.isRegularFile(path)
                ? new InstanceZipper<>(zip, path, ZOSPathFileZipper.getInstance())
                : new InstanceZipper<>(zip, path, ZOSPathDirectoryZipper.getInstance());
    }

    public static InstanceZipper<File, File> newZipper(File zip, File file) {
        return new InstanceZipper<>(Preconditions.parameterNotNull(zip, "zip"),
                Preconditions.parameterNotNull(file, "file"), FileFileZipper.getInstance());
    }

    public static InstanceZipper<File, File> newParallelZipper(File zip, File file) {
        return new InstanceZipper<>(Preconditions.parameterNotNull(zip, "zip"),
                Preconditions.parameterNotNull(file, "file"), FileFileParallelZipper.getInstance());
    }

    public static InstanceZipper<File, Path> newZipper(File zip, Path path) {
        return new InstanceZipper<>(Preconditions.parameterNotNull(zip, "zip"),
                Preconditions.parameterNotNull(path, "path"), FilePathZipper.getInstance());
    }

    public static InstanceZipper<File, Path> newParallelZipper(File zip, Path path) {
        return new InstanceZipper<>(Preconditions.parameterNotNull(zip, "zip"),
                Preconditions.parameterNotNull(path, "path"), FilePathParallelZipper.getInstance());
    }

    public static InstanceZipper<Path, Path> newZipper(Path zip, Path path) {
        return new InstanceZipper<>(Preconditions.parameterNotNull(zip, "zip"),
                Preconditions.parameterNotNull(path, "path"), PathPathZipper.getInstance());
    }

    public static InstanceZipper<Path, Path> newParallelZipper(Path zip, Path path) {
        return new InstanceZipper<>(Preconditions.parameterNotNull(zip, "zip"),
                Preconditions.parameterNotNull(path, "path"), PathPathParallelZipper.getInstance());
    }

    public static InstanceZipper<Path, File> newZipper(Path zip, File file) {
        return new InstanceZipper<>(Preconditions.parameterNotNull(zip, "zip"),
                Preconditions.parameterNotNull(file, "file"), PathFileZipper.getInstance());
    }

    public static InstanceZipper<Path, File> newParallelZipper(Path zip, File file) {
        return new InstanceZipper<>(Preconditions.parameterNotNull(zip, "zip"),
                Preconditions.parameterNotNull(file, "file"), PathFileParallelZipper.getInstance());
    }

    public static ZOSFileZipper getZOSFileZipper() {
        return ZOSFileZipper.getInstance();
    }
    public static ZOSDirectoryZipper getZOSDirectoryZipper() {
        return ZOSDirectoryZipper.getInstance();
    }
    public static ZOSPathFileZipper getZOSPathFileZipper() {
        return ZOSPathFileZipper.getInstance();
    }
    public static ZOSPathDirectoryZipper getZOSPathDirectoryZipper() {
        return ZOSPathDirectoryZipper.getInstance();
    }
    public static FileFileZipper getFileFileZipper() {
        return FileFileZipper.getInstance();
    }
    public static FileFileParallelZipper getFileFileParallelZipper() {
        return FileFileParallelZipper.getInstance();
    }
    public static FilePathZipper getFilePathZipper() {
        return FilePathZipper.getInstance();
    }
    public static FilePathParallelZipper getFilePathParallelZipper() {
        return FilePathParallelZipper.getInstance();
    }
    public static PathPathZipper getPathPathZipper() {
        return PathPathZipper.getInstance();
    }
    public static PathPathParallelZipper getPathPathParallelZipper() {
        return PathPathParallelZipper.getInstance();
    }
    public static PathFileZipper getPathFileZipper() {
        return PathFileZipper.getInstance();
    }
    public static PathFileParallelZipper getPathFileParallelZipper() {
        return PathFileParallelZipper.getInstance();
    }
}

