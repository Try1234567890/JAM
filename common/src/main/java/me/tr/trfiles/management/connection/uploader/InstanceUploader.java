package me.tr.trfiles.management.connection.uploader;

import com.github.utilities.validators.Preconditions;

import java.io.IOException;
import java.net.URL;

public record InstanceUploader<D>(URL url, D destination, Uploader<D> uploader) {

    public InstanceUploader(URL url, D destination, Uploader<D> uploader) {
        this.url = Preconditions.parameterNotNull(url, "url");
        this.destination = Preconditions.parameterNotNull(destination, "destination");
        this.uploader = Preconditions.parameterNotNull(uploader, "uploader");
    }

    public void uploadOrThrown() throws IOException {
        uploader.uploadOrThrown(url, destination);
    }

    public boolean upload() {
        return uploader.upload(url, destination);
    }
}