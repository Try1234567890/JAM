package com.github.trfiles.management.connection.uploader.file;

import com.github.trfiles.management.connection.uploader.Uploaders;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.InvalidPathException;

public class FileUploader extends IFileUploader {
    private FileUploader() {
    }

    private record Holder() {
        private static final FileUploader INSTANCE = new FileUploader();
    }

    public static FileUploader getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    public void uploadOrThrown(URL url, File source) throws IOException, InvalidPathException {
        Uploaders.getPathUploader()
                .uploadOrThrown(url, source.toPath());
    }
}