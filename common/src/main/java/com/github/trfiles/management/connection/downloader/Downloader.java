package com.github.trfiles.management.connection.downloader;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;

public abstract class Downloader<D> {

    public abstract D downloadOrThrown(URL url, D destination) throws IOException;

    public Optional<D> download(URL url, D destination) {
        try {
            return Optional.ofNullable(downloadOrThrown(url, destination));
        } catch (IOException ignored) {
            return Optional.empty();
        }
    }

    public D downloadOrNull(URL url, D destination) {
        return download(url, destination).orElse(null);
    }
}
