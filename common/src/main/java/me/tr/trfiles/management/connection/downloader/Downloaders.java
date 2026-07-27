package me.tr.trfiles.management.connection.downloader;

import com.github.utilities.validators.Preconditions;
import me.tr.trfiles.management.connection.downloader.path.ToPathDownloader;
import me.tr.trfiles.management.connection.downloader.path.ToPathParallelDownloader;
import me.tr.trfiles.management.connection.downloader.stream.ToStreamDownloader;
import me.tr.trfiles.management.connection.downloader.stream.ToStreamParallelDownloader;

import java.io.OutputStream;
import java.net.URL;
import java.nio.file.Path;

public class Downloaders {

    private Downloaders() {
    }


    public static InstanceDownloader<Path> newDownloader(URL url, Path destination) {
        return new InstanceDownloader<>(Preconditions.parameterNotNull(url, "url"),
                Preconditions.parameterNotNull(destination, "destination"), ToPathDownloader.getInstance());
    }

    public static InstanceDownloader<Path> newParallelDownloader(URL url, Path destination) {
        return new InstanceDownloader<>(Preconditions.parameterNotNull(url, "url"),
                Preconditions.parameterNotNull(destination, "destination"), ToPathParallelDownloader.getInstance());
    }

    public static InstanceDownloader<OutputStream> newDownloader(URL url, OutputStream destination) {
        return new InstanceDownloader<>(Preconditions.parameterNotNull(url, "url"),
                Preconditions.parameterNotNull(destination, "destination"), ToStreamDownloader.getInstance());
    }

    public static InstanceDownloader<OutputStream> newParallelDownloader(URL url, OutputStream destination) {
        return new InstanceDownloader<>(Preconditions.parameterNotNull(url, "url"),
                Preconditions.parameterNotNull(destination, "destination"), ToStreamParallelDownloader.getInstance());
    }

    public static ToPathDownloader getPathDownloader() {
        return ToPathDownloader.getInstance();
    }

    public static ToPathParallelDownloader getParallelPathDownloader() {
        return ToPathParallelDownloader.getInstance();
    }

    public static ToStreamDownloader getStreamDownloader() {
        return ToStreamDownloader.getInstance();
    }

    public static ToStreamParallelDownloader getParallelStreamDownloader() {
        return ToStreamParallelDownloader.getInstance();
    }
}
