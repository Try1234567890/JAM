package com.github.trfiles.management.managers;

import java.io.File;
import java.nio.file.Path;
import java.util.function.Supplier;

public final class FluentFileManager {

    private FluentFileManager() {}

    private static final class Holder {
        private static final FluentFileManager INSTANCE = new FluentFileManager();
    }

    public static FluentFileManager get() {
        return Holder.INSTANCE;
    }

    /**
     * Checks if the {@code path} or thrown the {@code exception} if is not readable.
     * <p>
     * This method returns {@link FluentFileManager} instance to allow Fluent API usage, like:
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
     * @return This {@link FluentFileManager} instance to allow Fluent API usage.
     * @throws E If the path is not readable
     */
    public <E extends Throwable> FluentFileManager isReadable(String path, Supplier<E> exception) throws E {
        if (!FileManager.isReadable(path)) throw exception.get();
        return this;
    }

    /**
     * Checks if the {@code path} or thrown the {@code exception} if is not readable.
     * <p>
     * This method returns {@link FluentFileManager} instance to allow Fluent API usage, like:
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
     * @return This {@link FluentFileManager} instance to allow Fluent API usage.
     * @throws E If the path is not readable
     */
    public <E extends Throwable> FluentFileManager isReadable(File path, Supplier<E> exception) throws E {
        if (!FileManager.isReadable(path)) throw exception.get();
        return this;
    }

    /**
     * Checks if the {@code path} or thrown the {@code exception} if is not readable.
     * <p>
     * This method returns {@link FluentFileManager} instance to allow Fluent API usage, like:
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
     * @return This {@link FluentFileManager} instance to allow Fluent API usage.
     * @throws E If the path is not readable
     */
    public <E extends Throwable> FluentFileManager isReadable(Path path, Supplier<E> exception) throws E {
        if (!FileManager.isReadable(path)) throw exception.get();
        return this;
    }

    /**
     * Checks if the {@code path} or thrown the {@code exception} if is not writable.
     * <p>
     * This method returns {@link FluentFileManager} instance to allow Fluent API usage, like:
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
     * @return This {@link FluentFileManager} instance to allow Fluent API usage.
     * @throws E If the path is not writable
     */
    public <E extends Throwable> FluentFileManager isWritable(String path, Supplier<E> exception) throws E {
        if (!FileManager.isWritable(path)) throw exception.get();
        return this;
    }

    /**
     * Checks if the {@code path} or thrown the {@code exception} if is not writable.
     * <p>
     * This method returns {@link FluentFileManager} instance to allow Fluent API usage, like:
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
     * @return This {@link FluentFileManager} instance to allow Fluent API usage.
     * @throws E If the path is not writable
     */
    public <E extends Throwable> FluentFileManager isWritable(File path, Supplier<E> exception) throws E {
        if (!FileManager.isWritable(path)) throw exception.get();
        return this;
    }

    /**
     * Checks if the {@code path} or thrown the {@code exception} if is not writable.
     * <p>
     * This method returns {@link FluentFileManager} instance to allow Fluent API usage, like:
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
     * @return This {@link FluentFileManager} instance to allow Fluent API usage.
     * @throws E If the path is not writable
     */
    public <E extends Throwable> FluentFileManager isWritable(Path path, Supplier<E> exception) throws E {
        if (!FileManager.isWritable(path)) throw exception.get();
        return this;
    }

    /**
     * Checks if the {@code path} or thrown the {@code exception} if is not executable.
     * <p>
     * This method returns {@link FluentFileManager} instance to allow Fluent API usage, like:
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
     * @return This {@link FluentFileManager} instance to allow Fluent API usage.
     * @throws E If the path is not executable
     */
    public <E extends Throwable> FluentFileManager isExecutable(String path, Supplier<E> exception) throws E {
        if (!FileManager.isExecutable(path)) throw exception.get();
        return this;
    }

    /**
     * Checks if the {@code path} or thrown the {@code exception} if is not executable.
     * <p>
     * This method returns {@link FluentFileManager} instance to allow Fluent API usage, like:
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
     * @return This {@link FluentFileManager} instance to allow Fluent API usage.
     * @throws E If the path is not executable
     */
    public <E extends Throwable> FluentFileManager isExecutable(File path, Supplier<E> exception) throws E {
        if (!FileManager.isExecutable(path)) throw exception.get();
        return this;
    }

    /**
     * Checks if the {@code path} or thrown the {@code exception} if is not executable.
     * <p>
     * This method returns {@link FluentFileManager} instance to allow Fluent API usage, like:
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
     * @return This {@link FluentFileManager} instance to allow Fluent API usage.
     * @throws E If the path is not executable
     */
    public <E extends Throwable> FluentFileManager isExecutable(Path path, Supplier<E> exception) throws E {
        if (!FileManager.isExecutable(path)) throw exception.get();
        return this;
    }

    /**
     * Checks if the {@code path} or thrown the {@code exception} if is not exists.
     * <p>
     * This method returns {@link FluentFileManager} instance to allow Fluent API usage, like:
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
     * @return This {@link FluentFileManager} instance to allow Fluent API usage.
     * @throws E If the path is not exists
     */
    public <E extends Throwable> FluentFileManager exists(String path, Supplier<E> exception) throws E {
        if (!FileManager.exists(path)) throw exception.get();
        return this;
    }


    /**
     * Checks if the {@code path} or thrown the {@code exception} if is not exists.
     * <p>
     * This method returns {@link FluentFileManager} instance to allow Fluent API usage, like:
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
     * @return This {@link FluentFileManager} instance to allow Fluent API usage.
     * @throws E If the path is not exists
     */
    public <E extends Throwable> FluentFileManager exists(File path, Supplier<E> exception) throws E {
        if (!FileManager.exists(path)) throw exception.get();
        return this;
    }

    /**
     * Checks if the {@code path} or thrown the {@code exception} if is not exists.
     * <p>
     * This method returns {@link FluentFileManager} instance to allow Fluent API usage, like:
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
     * @return This {@link FluentFileManager} instance to allow Fluent API usage.
     * @throws E If the path is not exists
     */
    public <E extends Throwable> FluentFileManager exists(Path path, Supplier<E> exception) throws E {
        if (!FileManager.exists(path)) throw exception.get();
        return this;
    }

    /**
     * Checks if the {@code path} or thrown the {@code exception} if is not a directory.
     * <p>
     * This method returns {@link FluentFileManager} instance to allow Fluent API usage, like:
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
     * @return This {@link FluentFileManager} instance to allow Fluent API usage.
     * @throws E If the path is not a directory
     */
    public <E extends Throwable> FluentFileManager isDirectory(String path, Supplier<E> exception) throws E {
        if (!FileManager.isDirectory(path)) throw exception.get();
        return this;
    }

    /**
     * Checks if the {@code path} or thrown the {@code exception} if is not a directory.
     * <p>
     * This method returns {@link FluentFileManager} instance to allow Fluent API usage, like:
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
     * @return This {@link FluentFileManager} instance to allow Fluent API usage.
     * @throws E If the path is not a directory
     */
    public <E extends Throwable> FluentFileManager isDirectory(File path, Supplier<E> exception) throws E {
        if (!FileManager.isDirectory(path)) throw exception.get();
        return this;
    }

    /**
     * Checks if the {@code path} or thrown the {@code exception} if is not a directory.
     * <p>
     * This method returns {@link FluentFileManager} instance to allow Fluent API usage, like:
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
     * @return This {@link FluentFileManager} instance to allow Fluent API usage.
     * @throws E If the path is not a directory
     */
    public <E extends Throwable> FluentFileManager isDirectory(Path path, Supplier<E> exception) throws E {
        if (!FileManager.isDirectory(path)) throw exception.get();
        return this;
    }

    /**
     * Checks if the {@code path} or thrown the {@code exception} if is not a regular file.
     * <p>
     * This method returns {@link FluentFileManager} instance to allow Fluent API usage, like:
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
     * @return This {@link FluentFileManager} instance to allow Fluent API usage.
     * @throws E If the path is not regular file
     */
    public <E extends Throwable> FluentFileManager isRegularFile(String path, Supplier<E> exception) throws E {
        if (!FileManager.isRegularFile(path)) throw exception.get();
        return this;
    }

    /**
     * Checks if the {@code path} or thrown the {@code exception} if is not a regular file.
     * <p>
     * This method returns {@link FluentFileManager} instance to allow Fluent API usage, like:
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
     * @return This {@link FluentFileManager} instance to allow Fluent API usage.
     * @throws E If the path is not regular file
     */
    public <E extends Throwable> FluentFileManager isRegularFile(File path, Supplier<E> exception) throws E {
        if (!FileManager.isRegularFile(path)) throw exception.get();
        return this;
    }

    /**
     * Checks if the {@code path} or thrown the {@code exception} if is not a regular file.
     * <p>
     * This method returns {@link FluentFileManager} instance to allow Fluent API usage, like:
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
     * @return This {@link FluentFileManager} instance to allow Fluent API usage.
     * @throws E If the path is not regular file
     */
    public <E extends Throwable> FluentFileManager isRegularFile(Path path, Supplier<E> exception) throws E {
        if (!FileManager.isRegularFile(path)) throw exception.get();
        return this;
    }
}
