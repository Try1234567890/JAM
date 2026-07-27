package me.tr.trfiles.management.connection.uploader.file;

import me.tr.trfiles.management.connection.uploader.Uploaders;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.InvalidPathException;

public class FileParallelUploader extends IFileUploader {
    private FileParallelUploader() {
    }

    private record Holder() {
        private static final FileParallelUploader INSTANCE = new FileParallelUploader();
    }

    public static FileParallelUploader getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    public void uploadOrThrown(URL url, File source) throws IOException, InvalidPathException {
        Uploaders.getParallelPathUploader()
                .uploadOrThrown(url, source.toPath());
    }
}