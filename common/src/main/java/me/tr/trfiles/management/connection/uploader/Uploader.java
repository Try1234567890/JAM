package me.tr.trfiles.management.connection.uploader;

import java.io.IOException;
import java.net.URL;

public abstract class Uploader<D> {

    public abstract void uploadOrThrown(URL url, D source) throws IOException;

    public boolean upload(URL url, D source) {
        try {
            uploadOrThrown(url, source);
            return true;
        } catch (IOException _) {
            return false;
        }
    }
}