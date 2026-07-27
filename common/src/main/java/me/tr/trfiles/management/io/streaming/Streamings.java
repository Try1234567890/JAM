package me.tr.trfiles.management.io.streaming;

import com.github.utilities.validators.Preconditions;
import me.tr.trfiles.management.io.streaming.file.FileToFileStreaming;
import me.tr.trfiles.management.io.streaming.file.FileToPathStreaming;
import me.tr.trfiles.management.io.streaming.file.FileToStreamStreaming;
import me.tr.trfiles.management.io.streaming.path.PathToFileStreaming;
import me.tr.trfiles.management.io.streaming.path.PathToPathStreaming;
import me.tr.trfiles.management.io.streaming.path.PathToStreamStreaming;
import me.tr.trfiles.management.io.streaming.stream.StreamToFileStreaming;
import me.tr.trfiles.management.io.streaming.stream.StreamToPathStreaming;
import me.tr.trfiles.management.io.streaming.stream.StreamToStreamStreaming;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Path;

public class Streamings {

    private Streamings() {
    }

    public static InstanceStreaming<InputStream, OutputStream> newStreaming(InputStream in, OutputStream out) {
        return new InstanceStreaming<>(Preconditions.parameterNotNull(in, "in"),
                Preconditions.parameterNotNull(out, "out"), StreamToStreamStreaming.getInstance());
    }

    public static InstanceStreaming<InputStream, Path> newStreaming(InputStream in, Path out) {
        return new InstanceStreaming<>(Preconditions.parameterNotNull(in, "in"),
                Preconditions.parameterNotNull(out, "out"), StreamToPathStreaming.getInstance());
    }

    public static InstanceStreaming<InputStream, File> newStreaming(InputStream in, File out) {
        return new InstanceStreaming<>(Preconditions.parameterNotNull(in, "in"),
                Preconditions.parameterNotNull(out, "out"), StreamToFileStreaming.getInstance());
    }

    public static InstanceStreaming<Path, OutputStream> newStreaming(Path in, OutputStream out) {
        return new InstanceStreaming<>(Preconditions.parameterNotNull(in, "in"),
                Preconditions.parameterNotNull(out, "out"), PathToStreamStreaming.getInstance());
    }

    public static InstanceStreaming<Path, Path> newStreaming(Path in, Path out) {
        return new InstanceStreaming<>(Preconditions.parameterNotNull(in, "in"),
                Preconditions.parameterNotNull(out, "out"), PathToPathStreaming.getInstance());
    }

    public static InstanceStreaming<Path, File> newStreaming(Path in, File out) {
        return new InstanceStreaming<>(Preconditions.parameterNotNull(in, "in"),
                Preconditions.parameterNotNull(out, "out"), PathToFileStreaming.getInstance());
    }

    public static InstanceStreaming<File, OutputStream> newStreaming(File in, OutputStream out) {
        return new InstanceStreaming<>(Preconditions.parameterNotNull(in, "in"),
                Preconditions.parameterNotNull(out, "out"), FileToStreamStreaming.getInstance());
    }

    public static InstanceStreaming<File, Path> newStreaming(File in, Path out) {
        return new InstanceStreaming<>(Preconditions.parameterNotNull(in, "in"),
                Preconditions.parameterNotNull(out, "out"), FileToPathStreaming.getInstance());
    }

    public static InstanceStreaming<File, File> newStreaming(File in, File out) {
        return new InstanceStreaming<>(Preconditions.parameterNotNull(in, "in"),
                Preconditions.parameterNotNull(out, "out"), FileToFileStreaming.getInstance());
    }

    public static StreamToStreamStreaming getStreamToStreamStreaming() {
        return StreamToStreamStreaming.getInstance();
    }

    public static StreamToPathStreaming getStreamToPathStreaming() {
        return StreamToPathStreaming.getInstance();
    }

    public static StreamToFileStreaming getStreamToFileStreaming() {
        return StreamToFileStreaming.getInstance();
    }

    public static PathToStreamStreaming getPathToStreamStreaming() {
        return PathToStreamStreaming.getInstance();
    }

    public static PathToPathStreaming getPathToPathStreaming() {
        return PathToPathStreaming.getInstance();
    }

    public static PathToFileStreaming getPathToFileStreaming() {
        return PathToFileStreaming.getInstance();
    }

    public static FileToStreamStreaming getFileToStreamStreaming() {
        return FileToStreamStreaming.getInstance();
    }

    public static FileToPathStreaming getFileToPathStreaming() {
        return FileToPathStreaming.getInstance();
    }

    public static FileToFileStreaming getFileToFileStreaming() {
        return FileToFileStreaming.getInstance();
    }

}
