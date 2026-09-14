package com.github.trfiles.management.managers;

import com.github.utilities.validators.Preconditions;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

public final class FileManager {

    private FileManager() {}

    /**
     * Checks if the given {@code path} is readable.
     *
     * @param path The path to check
     * @return {@code true} if is readable, otherwise {@code false}.
     */
    public static boolean isReadable(String path) {
        return isReadable(new File(Preconditions.parameterNotNull(path, "path")));
    }

    /**
     * Checks if the given {@code file} is readable.
     *
     * @param file The file to check
     * @return {@code true} if is readable, otherwise {@code false}.
     */
    public static boolean isReadable(File file) {
        return isReadable(Preconditions.simpleParameterNotNull(file, "file").toPath());
    }

    /**
     * Checks if the given {@code path} is readable.
     *
     * @param path The path to check
     * @return {@code true} if is readable, otherwise {@code false}.
     */
    public static boolean isReadable(Path path) {
        return Files.isReadable(Preconditions.parameterNotNull(path, "path"));
    }

    /**
     * Checks if the {@code path} is writable.
     *
     * @param path the path to check
     * @return {@code true} if is writable, otherwise {@code false}.
     */
    public static boolean isWritable(String path) {
        return isWritable(new File(Preconditions.parameterNotNull(path, "path")));
    }

    /**
     * Checks if the {@code path} is writable.
     *
     * @param path the path to check
     * @return {@code true} if is writable, otherwise {@code false}.
     */
    public static boolean isWritable(File path) {
        return isWritable(Preconditions.parameterNotNull(path, "path").toPath());
    }

    /**
     * Checks if the {@code path} is writable.
     *
     * @param path the path to check
     * @return {@code true} if is writable, otherwise {@code false}.
     */
    public static boolean isWritable(Path path) {
        return Files.isWritable(Preconditions.parameterNotNull(path, "path"));
    }

    /**
     * Checks if the {@code path} is executable.
     *
     * @param path The path to check.
     * @return {@code true} if is executable, otherwise {@code false}.
     */
    public static boolean isExecutable(String path) {
        return isExecutable(new File(Preconditions.parameterNotNull(path, "path")));
    }

    /**
     * Checks if the {@code path} is executable.
     *
     * @param path The path to check.
     * @return {@code true} if is executable, otherwise {@code false}.
     */
    public static boolean isExecutable(File path) {
        return isExecutable(Preconditions.parameterNotNull(path, "path").toPath());
    }

    /**
     * Checks if the {@code path} is executable.
     *
     * @param path The path to check.
     * @return {@code true} if is executable, otherwise {@code false}.
     */
    public static boolean isExecutable(Path path) {
        return Files.isExecutable(Preconditions.parameterNotNull(path, "path"));
    }

    /**
     * Checks if the {@code path} exists.
     *
     * @param path the path to check
     * @return {@code true} if exists, otherwise {@code false}.
     */
    public static boolean exists(String path) {
        return exists(new File(Preconditions.parameterNotNull(path, "path")));
    }

    /**
     * Checks if the {@code path} exists.
     *
     * @param path the path to check
     * @return {@code true} if exists, otherwise {@code false}.
     */
    public static boolean exists(File path) {
        return exists(Preconditions.parameterNotNull(path, "path").toPath());
    }

    /**
     * Checks if the {@code path} exists.
     *
     * @param path the path to check
     * @return {@code true} if exists, otherwise {@code false}.
     */
    public static boolean exists(Path path) {
        return Files.exists(Preconditions.parameterNotNull(path, "path"));
    }

    /**
     * Checks if the {@code path} is a directory.
     *
     * @param path the path to check
     * @return {@code true} if is a directory, otherwise {@code false}.
     */
    public static boolean isDirectory(String path) {
        return isDirectory(new File(Preconditions.parameterNotNull(path, "path")));
    }

    /**
     * Checks if the {@code path} is a directory.
     *
     * @param path the path to check
     * @return {@code true} if is a directory, otherwise {@code false}.
     */
    public static boolean isDirectory(File path) {
        return isDirectory(Preconditions.parameterNotNull(path, "path").toPath());
    }

    /**
     * Checks if the {@code path} is a directory.
     *
     * @param path the path to check
     * @return {@code true} if is a directory, otherwise {@code false}.
     */
    public static boolean isDirectory(Path path) {
        return Files.isDirectory(Preconditions.parameterNotNull(path, "path"));
    }

    /**
     * Checks if the {@code path} is a regular file.
     *
     * @param path the path to check
     * @return {@code true} if is a regular file, otherwise {@code false}.
     */
    public static boolean isRegularFile(String path) {
        return isRegularFile(new File(Preconditions.parameterNotNull(path, "path")));
    }

    /**
     * Checks if the {@code path} is a regular file.
     *
     * @param path the path to check
     * @return {@code true} if is a regular file, otherwise {@code false}.
     */
    public static boolean isRegularFile(File path) {
        return isRegularFile(Preconditions.parameterNotNull(path, "path").toPath());
    }

    /**
     * Checks if the {@code path} is a regular file.
     *
     * @param path the path to check
     * @return {@code true} if is a regular file, otherwise {@code false}.
     */
    public static boolean isRegularFile(Path path) {
        return Files.isRegularFile(Preconditions.parameterNotNull(path, "path"));
    }

    /**
     * Creates a new input stream for {@code path}.
     *
     * @param path the path
     * @return a new input stream
     * @throws IOException if any error occurs.
     */
    public static InputStream newInputStream(String path, OpenOption... options) throws IOException {
        return newInputStream(new File(Preconditions.parameterNotNull(path, "path")), options);
    }

    /**
     * Creates a new input stream for {@code path}.
     *
     * @param path the path
     * @return a new input stream
     * @throws IOException if any error occurs.
     */
    public static InputStream newInputStream(File path, OpenOption... options) throws IOException {
        return newInputStream(Preconditions.parameterNotNull(path, "path").toPath(), options);
    }

    /**
     * Creates a new input stream for {@code path}.
     *
     * @param path the path
     * @return a new input stream
     * @throws IOException if any error occurs.
     */
    public static InputStream newInputStream(Path path, OpenOption... options) throws IOException {
        return Files.newInputStream(Preconditions.parameterNotNull(path, "path"), options);
    }

    /**
     * Creates a new output stream for {@code path}.
     *
     * @param path the path
     * @return a new output stream
     * @throws IOException if any error occurs.
     */
    public static OutputStream newOutputStream(String path, OpenOption... options) throws IOException {
        return newOutputStream(new File(Preconditions.parameterNotNull(path, "path")), options);
    }

    /**
     * Creates a new output stream for {@code path}.
     *
     * @param path the path
     * @return a new output stream
     * @throws IOException if any error occurs.
     */
    public static OutputStream newOutputStream(File path, OpenOption... options) throws IOException {
        return newOutputStream(Preconditions.parameterNotNull(path, "path").toPath(), options);
    }

    /**
     * Creates a new output stream for {@code path}.
     *
     * @param path the path
     * @return a new output stream
     * @throws IOException if any error occurs.
     */
    public static OutputStream newOutputStream(Path path, OpenOption... options) throws IOException {
        return Files.newOutputStream(Preconditions.parameterNotNull(path, "path"), options);
    }

    public static List<Path> list(Path dir) throws IOException {
        if (!Files.isDirectory(dir)) return List.of(dir);

        try (Stream<Path> stream = Files.list(dir)) {
            return stream.toList();
        }
    }

}
