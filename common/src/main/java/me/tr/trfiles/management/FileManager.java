package me.tr.trfiles.management;

import com.github.utilities.validators.Preconditions;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.util.function.Supplier;

public class FileManager {

    private FileManager() {
    }

    private record Holder() {
        private static final FileManager INSTANCE = new FileManager();
    }

    public static FileManager getInstance() {
        return Holder.INSTANCE;
    }

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
     * Checks if the given {@code path} is readable.
     *
     * @param path The path to check
     * @return {@code true} if is readable, otherwise {@code false}.
     */
    public static boolean isReadable(File path) {
        return isReadable(Preconditions.parameterNotNull(path, "path").toPath());
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
     * Checks if the {@code path} or thrown the {@code exception} if is not readable.
     * <p>
     * This method returns {@link FileManager} instance to allow Fluent API usage, like:
     * <pre>
     *     {@code
     *          FileManager.getInstance()
     *              .exists(path, () -> new RuntimeException("The file at " + path + " not exists!"))
     *              .isReadable(path, () -> new RuntimeException("The file at " + path + " is not readable!"))
     *              .isWritable(path, () -> new RuntimeException("The file at " + path + " is not writable!"));
     *     }
     * </pre>
     *
     * @param path      The path to check
     * @param exception The exception to thrown
     * @param <E>       The type of the exception
     * @return This {@link FileManager} instance to allow Fluent API usage.
     * @throws E If the path is not readable
     */
    public <E extends Throwable> FileManager isReadable(String path, Supplier<E> exception) throws E {
        if (!isReadable(path)) throw exception.get();
        return this;
    }

    /**
     * Checks if the {@code path} or thrown the {@code exception} if is not readable.
     * <p>
     * This method returns {@link FileManager} instance to allow Fluent API usage, like:
     * <pre>
     *     {@code
     *          FileManager.getInstance()
     *              .exists(path, () -> new RuntimeException("The file at " + path + " not exists!"))
     *              .isReadable(path, () -> new RuntimeException("The file at " + path + " is not readable!"))
     *              .isWritable(path, () -> new RuntimeException("The file at " + path + " is not writable!"));
     *     }
     * </pre>
     *
     * @param path      The path to check
     * @param exception The exception to thrown
     * @param <E>       The type of the exception
     * @return This {@link FileManager} instance to allow Fluent API usage.
     * @throws E If the path is not readable
     */
    public <E extends Throwable> FileManager isReadable(File path, Supplier<E> exception) throws E {
        if (!isReadable(path)) throw exception.get();
        return this;
    }

    /**
     * Checks if the {@code path} or thrown the {@code exception} if is not readable.
     * <p>
     * This method returns {@link FileManager} instance to allow Fluent API usage, like:
     * <pre>
     *     {@code
     *          FileManager.getInstance()
     *              .exists(path, () -> new RuntimeException("The file at " + path + " not exists!"))
     *              .isReadable(path, () -> new RuntimeException("The file at " + path + " is not readable!"))
     *              .isWritable(path, () -> new RuntimeException("The file at " + path + " is not writable!"));
     *     }
     * </pre>
     *
     * @param path      The path to check
     * @param exception The exception to thrown
     * @param <E>       The type of the exception
     * @return This {@link FileManager} instance to allow Fluent API usage.
     * @throws E If the path is not readable
     */
    public <E extends Throwable> FileManager isReadable(Path path, Supplier<E> exception) throws E {
        if (!isReadable(path)) throw exception.get();
        return this;
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
     * Checks if the {@code path} or thrown the {@code exception} if is not writable.
     * <p>
     * This method returns {@link FileManager} instance to allow Fluent API usage, like:
     * <pre>
     *     {@code
     *          FileManager.getInstance()
     *              .exists(path, () -> new RuntimeException("The file at " + path + " not exists!"))
     *              .isReadable(path, () -> new RuntimeException("The file at " + path + " is not readable!"))
     *              .isWritable(path, () -> new RuntimeException("The file at " + path + " is not writable!"));
     *     }
     * </pre>
     *
     * @param path      The path to check
     * @param exception The exception to thrown
     * @param <E>       The type of the exception
     * @return This {@link FileManager} instance to allow Fluent API usage.
     * @throws E If the path is not writable
     */
    public <E extends Throwable> FileManager isWritable(String path, Supplier<E> exception) throws E {
        if (!isWritable(path)) throw exception.get();
        return this;
    }

    /**
     * Checks if the {@code path} or thrown the {@code exception} if is not writable.
     * <p>
     * This method returns {@link FileManager} instance to allow Fluent API usage, like:
     * <pre>
     *     {@code
     *          FileManager.getInstance()
     *              .exists(path, () -> new RuntimeException("The file at " + path + " not exists!"))
     *              .isReadable(path, () -> new RuntimeException("The file at " + path + " is not readable!"))
     *              .isWritable(path, () -> new RuntimeException("The file at " + path + " is not writable!"));
     *     }
     * </pre>
     *
     * @param path      The path to check
     * @param exception The exception to thrown
     * @param <E>       The type of the exception
     * @return This {@link FileManager} instance to allow Fluent API usage.
     * @throws E If the path is not writable
     */
    public <E extends Throwable> FileManager isWritable(File path, Supplier<E> exception) throws E {
        if (!isWritable(path)) throw exception.get();
        return this;
    }

    /**
     * Checks if the {@code path} or thrown the {@code exception} if is not writable.
     * <p>
     * This method returns {@link FileManager} instance to allow Fluent API usage, like:
     * <pre>
     *     {@code
     *          FileManager.getInstance()
     *              .exists(path, () -> new RuntimeException("The file at " + path + " not exists!"))
     *              .isReadable(path, () -> new RuntimeException("The file at " + path + " is not readable!"))
     *              .isWritable(path, () -> new RuntimeException("The file at " + path + " is not writable!"));
     *     }
     * </pre>
     *
     * @param path      The path to check
     * @param exception The exception to thrown
     * @param <E>       The type of the exception
     * @return This {@link FileManager} instance to allow Fluent API usage.
     * @throws E If the path is not writable
     */
    public <E extends Throwable> FileManager isWritable(Path path, Supplier<E> exception) throws E {
        if (!isWritable(path)) throw exception.get();
        return this;
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
     * Checks if the {@code path} or thrown the {@code exception} if is not executable.
     * <p>
     * This method returns {@link FileManager} instance to allow Fluent API usage, like:
     * <pre>
     *     {@code
     *          FileManager.getInstance()
     *              .exists(path, () -> new RuntimeException("The file at " + path + " not exists!"))
     *              .isReadable(path, () -> new RuntimeException("The file at " + path + " is not readable!"))
     *              .isWritable(path, () -> new RuntimeException("The file at " + path + " is not writable!"));
     *     }
     * </pre>
     *
     * @param path      The path to check
     * @param exception The exception to thrown
     * @param <E>       The type of the exception
     * @return This {@link FileManager} instance to allow Fluent API usage.
     * @throws E If the path is not executable
     */
    public <E extends Throwable> FileManager isExecutable(String path, Supplier<E> exception) throws E {
        if (!isExecutable(path)) throw exception.get();
        return this;
    }

    /**
     * Checks if the {@code path} or thrown the {@code exception} if is not executable.
     * <p>
     * This method returns {@link FileManager} instance to allow Fluent API usage, like:
     * <pre>
     *     {@code
     *          FileManager.getInstance()
     *              .exists(path, () -> new RuntimeException("The file at " + path + " not exists!"))
     *              .isReadable(path, () -> new RuntimeException("The file at " + path + " is not readable!"))
     *              .isWritable(path, () -> new RuntimeException("The file at " + path + " is not writable!"));
     *     }
     * </pre>
     *
     * @param path      The path to check
     * @param exception The exception to thrown
     * @param <E>       The type of the exception
     * @return This {@link FileManager} instance to allow Fluent API usage.
     * @throws E If the path is not executable
     */
    public <E extends Throwable> FileManager isExecutable(File path, Supplier<E> exception) throws E {
        if (!isExecutable(path)) throw exception.get();
        return this;
    }

    /**
     * Checks if the {@code path} or thrown the {@code exception} if is not executable.
     * <p>
     * This method returns {@link FileManager} instance to allow Fluent API usage, like:
     * <pre>
     *     {@code
     *          FileManager.getInstance()
     *              .exists(path, () -> new RuntimeException("The file at " + path + " not exists!"))
     *              .isReadable(path, () -> new RuntimeException("The file at " + path + " is not readable!"))
     *              .isWritable(path, () -> new RuntimeException("The file at " + path + " is not writable!"));
     *     }
     * </pre>
     *
     * @param path      The path to check
     * @param exception The exception to thrown
     * @param <E>       The type of the exception
     * @return This {@link FileManager} instance to allow Fluent API usage.
     * @throws E If the path is not executable
     */
    public <E extends Throwable> FileManager isExecutable(Path path, Supplier<E> exception) throws E {
        if (!isExecutable(path)) throw exception.get();
        return this;
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
     * Checks if the {@code path} or thrown the {@code exception} if is not exists.
     * <p>
     * This method returns {@link FileManager} instance to allow Fluent API usage, like:
     * <pre>
     *     {@code
     *          FileManager.getInstance()
     *              .exists(path, () -> new RuntimeException("The file at " + path + " not exists!"))
     *              .isReadable(path, () -> new RuntimeException("The file at " + path + " is not readable!"))
     *              .isWritable(path, () -> new RuntimeException("The file at " + path + " is not writable!"));
     *     }
     * </pre>
     *
     * @param path      The path to check
     * @param exception The exception to thrown
     * @param <E>       The type of the exception
     * @return This {@link FileManager} instance to allow Fluent API usage.
     * @throws E If the path is not exists
     */
    public <E extends Throwable> FileManager exists(String path, Supplier<E> exception) throws E {
        if (!exists(path)) throw exception.get();
        return this;
    }


    /**
     * Checks if the {@code path} or thrown the {@code exception} if is not exists.
     * <p>
     * This method returns {@link FileManager} instance to allow Fluent API usage, like:
     * <pre>
     *     {@code
     *          FileManager.getInstance()
     *              .exists(path, () -> new RuntimeException("The file at " + path + " not exists!"))
     *              .isReadable(path, () -> new RuntimeException("The file at " + path + " is not readable!"))
     *              .isWritable(path, () -> new RuntimeException("The file at " + path + " is not writable!"));
     *     }
     * </pre>
     *
     * @param path      The path to check
     * @param exception The exception to thrown
     * @param <E>       The type of the exception
     * @return This {@link FileManager} instance to allow Fluent API usage.
     * @throws E If the path is not exists
     */
    public <E extends Throwable> FileManager exists(File path, Supplier<E> exception) throws E {
        if (!exists(path)) throw exception.get();
        return this;
    }


    /**
     * Checks if the {@code path} or thrown the {@code exception} if is not exists.
     * <p>
     * This method returns {@link FileManager} instance to allow Fluent API usage, like:
     * <pre>
     *     {@code
     *          FileManager.getInstance()
     *              .exists(path, () -> new RuntimeException("The file at " + path + " not exists!"))
     *              .isReadable(path, () -> new RuntimeException("The file at " + path + " is not readable!"))
     *              .isWritable(path, () -> new RuntimeException("The file at " + path + " is not writable!"));
     *     }
     * </pre>
     *
     * @param path      The path to check
     * @param exception The exception to thrown
     * @param <E>       The type of the exception
     * @return This {@link FileManager} instance to allow Fluent API usage.
     * @throws E If the path is not exists
     */
    public <E extends Throwable> FileManager exists(Path path, Supplier<E> exception) throws E {
        if (!exists(path)) throw exception.get();
        return this;
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
     * Checks if the {@code path} or thrown the {@code exception} if is not a directory.
     * <p>
     * This method returns {@link FileManager} instance to allow Fluent API usage, like:
     * <pre>
     *     {@code
     *          FileManager.getInstance()
     *              .exists(path, () -> new RuntimeException("The file at " + path + " not exists!"))
     *              .isReadable(path, () -> new RuntimeException("The file at " + path + " is not readable!"))
     *              .isWritable(path, () -> new RuntimeException("The file at " + path + " is not writable!"));
     *     }
     * </pre>
     *
     * @param path      The path to check
     * @param exception The exception to thrown
     * @param <E>       The type of the exception
     * @return This {@link FileManager} instance to allow Fluent API usage.
     * @throws E If the path is not a directory
     */
    public <E extends Throwable> FileManager isDirectory(String path, Supplier<E> exception) throws E {
        if (!isDirectory(path)) throw exception.get();
        return this;
    }

    /**
     * Checks if the {@code path} or thrown the {@code exception} if is not a directory.
     * <p>
     * This method returns {@link FileManager} instance to allow Fluent API usage, like:
     * <pre>
     *     {@code
     *          FileManager.getInstance()
     *              .exists(path, () -> new RuntimeException("The file at " + path + " not exists!"))
     *              .isReadable(path, () -> new RuntimeException("The file at " + path + " is not readable!"))
     *              .isWritable(path, () -> new RuntimeException("The file at " + path + " is not writable!"));
     *     }
     * </pre>
     *
     * @param path      The path to check
     * @param exception The exception to thrown
     * @param <E>       The type of the exception
     * @return This {@link FileManager} instance to allow Fluent API usage.
     * @throws E If the path is not a directory
     */
    public <E extends Throwable> FileManager isDirectory(File path, Supplier<E> exception) throws E {
        if (!isDirectory(path)) throw exception.get();
        return this;
    }

    /**
     * Checks if the {@code path} or thrown the {@code exception} if is not a directory.
     * <p>
     * This method returns {@link FileManager} instance to allow Fluent API usage, like:
     * <pre>
     *     {@code
     *          FileManager.getInstance()
     *              .exists(path, () -> new RuntimeException("The file at " + path + " not exists!"))
     *              .isReadable(path, () -> new RuntimeException("The file at " + path + " is not readable!"))
     *              .isWritable(path, () -> new RuntimeException("The file at " + path + " is not writable!"));
     *     }
     * </pre>
     *
     * @param path      The path to check
     * @param exception The exception to thrown
     * @param <E>       The type of the exception
     * @return This {@link FileManager} instance to allow Fluent API usage.
     * @throws E If the path is not a directory
     */
    public <E extends Throwable> FileManager isDirectory(Path path, Supplier<E> exception) throws E {
        if (!isDirectory(path)) throw exception.get();
        return this;
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
     * Checks if the {@code path} or thrown the {@code exception} if is not a regular file.
     * <p>
     * This method returns {@link FileManager} instance to allow Fluent API usage, like:
     * <pre>
     *     {@code
     *          FileManager.getInstance()
     *              .exists(path, () -> new RuntimeException("The file at " + path + " not exists!"))
     *              .isReadable(path, () -> new RuntimeException("The file at " + path + " is not readable!"))
     *              .isWritable(path, () -> new RuntimeException("The file at " + path + " is not writable!"));
     *     }
     * </pre>
     *
     * @param path      The path to check
     * @param exception The exception to thrown
     * @param <E>       The type of the exception
     * @return This {@link FileManager} instance to allow Fluent API usage.
     * @throws E If the path is not regular file
     */
    public <E extends Throwable> FileManager isRegularFile(String path, Supplier<E> exception) throws E {
        if (!isRegularFile(path)) throw exception.get();
        return this;
    }

    /**
     * Checks if the {@code path} or thrown the {@code exception} if is not a regular file.
     * <p>
     * This method returns {@link FileManager} instance to allow Fluent API usage, like:
     * <pre>
     *     {@code
     *          FileManager.getInstance()
     *              .exists(path, () -> new RuntimeException("The file at " + path + " not exists!"))
     *              .isReadable(path, () -> new RuntimeException("The file at " + path + " is not readable!"))
     *              .isWritable(path, () -> new RuntimeException("The file at " + path + " is not writable!"));
     *     }
     * </pre>
     *
     * @param path      The path to check
     * @param exception The exception to thrown
     * @param <E>       The type of the exception
     * @return This {@link FileManager} instance to allow Fluent API usage.
     * @throws E If the path is not regular file
     */
    public <E extends Throwable> FileManager isRegularFile(File path, Supplier<E> exception) throws E {
        if (!isRegularFile(path)) throw exception.get();
        return this;
    }

    /**
     * Checks if the {@code path} or thrown the {@code exception} if is not a regular file.
     * <p>
     * This method returns {@link FileManager} instance to allow Fluent API usage, like:
     * <pre>
     *     {@code
     *          FileManager.getInstance()
     *              .exists(path, () -> new RuntimeException("The file at " + path + " not exists!"))
     *              .isReadable(path, () -> new RuntimeException("The file at " + path + " is not readable!"))
     *              .isWritable(path, () -> new RuntimeException("The file at " + path + " is not writable!"));
     *     }
     * </pre>
     *
     * @param path      The path to check
     * @param exception The exception to thrown
     * @param <E>       The type of the exception
     * @return This {@link FileManager} instance to allow Fluent API usage.
     * @throws E If the path is not regular file
     */
    public <E extends Throwable> FileManager isRegularFile(Path path, Supplier<E> exception) throws E {
        if (!isRegularFile(path)) throw exception.get();
        return this;
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

}
