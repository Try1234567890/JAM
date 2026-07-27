package me.tr.trfiles.management.archives.zipper.zipfile.path;

import me.tr.trfiles.management.FileCreator;
import me.tr.trfiles.management.FileUtility;
import me.tr.trfiles.management.archives.zipper.Zipper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public abstract class IPathZipper<V> extends Zipper<Path, V> {

    protected abstract void zipOrThrown0(Path zip, V value) throws IOException;

    @Override
    public void zipOrThrown(Path zip, V value) throws IOException {
        checkZIP(zip);
        zipOrThrown0(zip, value);
    }

    protected void checkZIP(Path value) {
        if (!Files.exists(value)) {
            throw new IllegalArgumentException("The path " + value + " doesn't exists!");
        }
        if (!FileUtility.isZip(value)) {
            throw new IllegalArgumentException("The path " + value + " is not a zip file!");
        }
        if (!Files.isWritable(value)) {
            throw new IllegalArgumentException("The path " + value + " is not readable!");
        }
    }

    protected void checkReadable(Path value) {
        if (!Files.exists(value)) {
            Throwable error = FileCreator.newFile(value).error();
            if (error != null) {
                throw new IllegalArgumentException("The path " + value + " doesn't exists and cannot be created!", error);
            }
        }
        if (!Files.isReadable(value)) {
            throw new IllegalArgumentException("The path " + value + " is not writable!");
        }
    }


}
