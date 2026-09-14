package com.github.trfiles.management;

import com.github.trfiles.management.io.writers.Writer;
import com.github.trfiles.management.io.writers.toPath.BytesPathWriter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.function.Function;

/**
 * Utility class providing static methods to safely create and delete files
 * and directories with detailed result handling.
 */
public class FileCreator {
    public static final Function<Path, BytesPathWriter> EMPTY_ZIP = p -> Writer.builder().toPath(p)
            .withBytes(new byte[]{80, 75, 5, 6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});

    /**
     * Private constructor to prevent instantiation of this utility class.
     */
    private FileCreator() {
    }


    /**
     * Creates a new empty ZIP file at {@code path}
     *
     * @param path The path of the new ZIP
     * @return a {@link Result} instance indicating the outcome of the operation.
     */
    public static @NotNull Result newZIP(Path path) {
        try {
            Result newFile = newFile(path);
            if (!newFile.isSuccess())
                return newFile;

            if (newFile.status() == Status.ALREADY_EXISTS
                    && !FileUtility.isZip(path)) {
                return new Result(Status.DIFFERENT_TYPE, new IOException("A file or directory already exists at " + path + " but is not a ZIP file."));
            }

            EMPTY_ZIP.apply(path).write();
            return new Result(Status.SUCCESS, null);
        } catch (Exception e) {
            return new Result(Status.ERROR_OCCURS, e);
        }
    }

    public static @NotNull Result newZIP(File path) {
        return newZIP(path.toPath());
    }

    public static @NotNull Result newZIP(String path) {
        return newZIP(Paths.get(path));
    }


    /**
     * Creates a new file at the specified {@link Path}. Parent directories
     * will <b>NOT</b> be created if they do not exist.
     *
     * @param path the target path where the file should be created.
     * @return a {@link Result} instance indicating the outcome of the operation.
     */
    public static @NotNull Result newSimpleFile(Path path) {
        try {
            if (Files.exists(path)) return new Result(Status.ALREADY_EXISTS, null);
            if (Files.isDirectory(path)) return new Result(Status.DIFFERENT_TYPE,
                    new IOException("The path \"" + path + "\" points to a directory!"));

            Files.createFile(path);
            return new Result(Status.SUCCESS, null);
        } catch (IOException e) {
            return new Result(Status.ERROR_OCCURS, e);
        }
    }

    public static @NotNull Result newSimpleFile(File file) {
        return newSimpleFile(file.toPath());
    }

    public static @NotNull Result newSimpleFile(String path) {
        return newSimpleFile(Paths.get(path));
    }

    /**
     * Creates a new file at the specified {@link Path}. Parent directories
     * will be created automatically if they do not exist.
     *
     * @param path the target path where the file should be created.
     * @return a {@link Result} instance indicating the outcome of the operation.
     */
    public static @NotNull Result newFile(Path path) {
        try {
            if (Files.exists(path)) return new Result(Status.ALREADY_EXISTS, null);
            if (Files.isDirectory(path)) return new Result(Status.DIFFERENT_TYPE,
                    new IOException("The path \"" + path + "\" points to a directory!"));

            Result newFolderResult = newDirectories(path.getParent());

            if (newFolderResult.error != null) {
                return new Result(Status.ERROR_OCCURS, newFolderResult.error);
            }

            Files.createFile(path);
            return new Result(Status.SUCCESS, null);
        } catch (IOException e) {
            return new Result(Status.ERROR_OCCURS, e);
        }
    }

    /**
     * Creates a new file at the specified {@link File} location.
     *
     * @param path the target file object to create.
     * @return a {@link Result} instance indicating the outcome of the operation.
     * @see #newFile(Path)
     */
    public static @NotNull Result newFile(File path) {
        return newFile(path.toPath());
    }

    /**
     * Creates a new file at the specified string path location.
     *
     * @param path the path string representing the file destination.
     * @return a {@link Result} instance indicating the outcome of the operation.
     * @see #newFile(Path)
     */
    public static @NotNull Result newFile(String path) {
        return newFile(Paths.get(path));
    }

    /**
     * Creates directories at the specified {@link Path}, including any necessary
     * parent directories.
     *
     * @param path the path pointing to the directories to create.
     * @return a {@link Result} instance indicating the outcome of the operation.
     */
    public static @NotNull Result newDirectories(Path path) {
        if (Files.exists(path)) return new Result(Status.ALREADY_EXISTS, null);
        if (Files.isRegularFile(path)) return new Result(Status.DIFFERENT_TYPE,
                new IOException("The path \"" + path + "\" points to a regular file!"));
        try {
            Files.createDirectories(path);
            return new Result(Status.SUCCESS, null);
        } catch (IOException e) {
            return new Result(Status.ERROR_OCCURS, e);
        }
    }

    /**
     * Creates directories at the specified {@link File} location.
     *
     * @param file the file object representing the directory path.
     * @return a {@link Result} instance indicating the outcome of the operation.
     * @see #newDirectories(Path)
     */
    public static @NotNull Result newDirectories(File file) {
        return newDirectories(file.toPath());
    }

    /**
     * Creates directories at the specified string path location.
     *
     * @param file the path string representing the directory path.
     * @return a {@link Result} instance indicating the outcome of the operation.
     * @see #newDirectories(Path)
     */
    public static @NotNull Result newDirectories(String file) {
        return newDirectories(Paths.get(file));
    }

    /**
     * Deletes the file or directory at the specified {@link Path}.
     *
     * @param file the target path to delete.
     * @return a {@link Result} instance indicating the outcome of the operation.
     */
    public static @NotNull Result delete(Path file) {
        try {
            if (!Files.exists(file)) return new Result(Status.ALREADY_NOT_EXISTS, null);
            Files.delete(file);
            return new Result(Status.SUCCESS, null);
        } catch (IOException e) {
            return new Result(Status.ERROR_OCCURS, e);
        }
    }

    /**
     * Deletes the specified {@link File}.
     *
     * @param file the target file to delete.
     * @return a {@link Result} instance indicating the outcome of the operation.
     * @see #delete(Path)
     */
    public static @NotNull Result delete(File file) {
        return delete(file.toPath());
    }

    /**
     * Deletes the file or directory at the specified path string.
     * * @param file the path string of the target to delete.
     *
     * @return a {@link Result} instance indicating the outcome of the operation.
     * @see #delete(Path)
     */
    public static @NotNull Result delete(String file) {
        return delete(Paths.get(file));
    }

    /**
     * Deletes the file or directory at the specified {@link Path} and all
     * parent directories.
     *
     * @param file the target path to delete.
     * @return a {@link Result} instance indicating the outcome of the operation.
     */
    public static @NotNull Result deleteAll(Path file) {
        if (!Files.exists(file)) return new Result(Status.ALREADY_NOT_EXISTS, null);
        Path parent = file;

        while ((parent = parent.getParent()) != null) {
            Result result = delete(parent);
            if (!result.isSuccess()) return result;
        }

        return new Result(Status.SUCCESS, null);
    }

    /**
     * Deletes the specified {@link File} and all parent directories.
     *
     * @param file the target file to delete.
     * @return a {@link Result} instance indicating the outcome of the operation.
     * @see #delete(Path)
     */
    public static @NotNull Result deleteAll(File file) {
        return delete(file.toPath());
    }

    /**
     * Deletes the file or directory at the specified path string and all parent directories.
     *
     * @param file the path string of the target to delete.
     * @return a {@link Result} instance indicating the outcome of the operation.
     * @see #delete(Path)
     */
    public static @NotNull Result deleteAll(String file) {
        return delete(Paths.get(file));
    }

    /**
     * Represents the outcome of an operation performed by {@link FileCreator}.
     *
     * @param status the categorized status for the operation result.
     * @param error  the exception that occurred during execution, or {@code null} if no error occurred.
     */
    public record Result(Status status, @Nullable Throwable error) {

        /**
         * Determines if the operation completed without throwing an error.
         *
         * @return {@code true} if no error occurred ({@code error == null}), otherwise {@code false}.
         * @see Status#isSuccess()
         */
        public boolean isSuccess() {
            return error == null;
        }

    }

    /**
     * Enumeration representing the specific outcome status of a file system operation.
     */
    public enum Status {

        /**
         * Indicates the operation completed successfully.
         */
        SUCCESS(true),

        /**
         * Indicates the target file or directory already exists.
         */
        ALREADY_EXISTS(true),

        /**
         * Indicates the target file or directory already does not exist.
         */
        ALREADY_NOT_EXISTS(true),

        /**
         * Indicates the target path points to a different type than expected (e.g., directory instead of a regular file).
         */
        DIFFERENT_TYPE(false),

        /**
         * Indicates an unexpected error occurred during execution.
         */
        ERROR_OCCURS(false);

        private final boolean success;

        Status(boolean success) {
            this.success = success;
        }

        /**
         * Indicates whether this status is considered a successful state.
         * <p>
         * Note: A result marked as successful does not guarantee that file system modifications occurred.
         * For example, {@link #ALREADY_EXISTS} is considered successful because the desired end state
         * (the file existing) is met, even though no new file was created.
         * </p>
         *
         * @return {@code true} if the result state is considered successful; {@code false} otherwise.
         */
        public boolean isSuccess() {
            return success;
        }
    }

}