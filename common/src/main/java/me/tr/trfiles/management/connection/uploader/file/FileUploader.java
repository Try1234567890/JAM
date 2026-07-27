package me.tr.trfiles.management.connection.uploader.file;

import me.tr.trfiles.management.connection.uploader.Uploaders;
import me.tr.trfiles.management.io.streaming.Streamings;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;

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