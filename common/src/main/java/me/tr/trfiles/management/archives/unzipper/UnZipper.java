package me.tr.trfiles.management.archives.unzipper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

public abstract class UnZipper<T, V> {

    public abstract V unzipOrThrown(T zip, V to) throws IOException;

    public Optional<V> unzip(T zip, V to) {
        try {
            return Optional.ofNullable(unzipOrThrown(zip, to));
        } catch (IOException _) {
            return Optional.empty();
        }
    }

    public V unzipOrNull(T zip, V to) {
        try {
            return unzipOrThrown(zip, to);
        } catch (IOException _) {
            return null;
        }
    }

    protected void checkForZipSlip(Path targetDir, Path zipEntryFile) {
        String canonicalTargetDirPath = targetDir.normalize().toString();
        String canonicalZipEntryPath = zipEntryFile.normalize().toString();

        if (!canonicalZipEntryPath.startsWith(canonicalTargetDirPath + File.separator)) {
            throw new SecurityException("Zip Slip detected! Entry " + zipEntryFile + " point outside destination folder.");
        }
    }
}
