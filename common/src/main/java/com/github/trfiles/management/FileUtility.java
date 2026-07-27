package com.github.trfiles.management;

import com.github.utilities.validators.Preconditions;
import com.github.trfiles.management.io.reader.Readers;
import com.github.trfiles.os.OSUtility;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

/**
 * Utility file class for files
 */
public class FileUtility {

    private FileUtility() throws InstantiationException {
        throw new InstantiationException("Cannot instantiate utility class.");
    }

    /**
     * Get only the file name from its String path.
     *
     * @param path The file path as String
     * @return Only the file name.
     */
    public static String getFileName(String path) {
        Preconditions.parameterNotNull(path, "path");

        String newPath = OSUtility.toSlash(path);
        int index = newPath.lastIndexOf('/');
        return newPath.substring(index != -1 ? (index + 1) : 0);
    }

    /**
     * Check if the file name has an extension.
     * <p>
     * If the file name doesn't contain {@code .} return {@code false},
     * else if getting char at index of last {@code .} found in string + 1
     * doesn't throw the {@link IndexOutOfBoundsException} return {@code true}, otherwise {@code false}.
     *
     * @param name File name to check for extension from.
     * @return {@code true} if the file has an extension, otherwise {@code false}
     * @see #hasFileExtension(File)
     */
    public static boolean hasFileExtension(String name) {
        Preconditions.parameterNotNull(name, "name");

        int index = name.lastIndexOf(".");
        if (index != -1) {
            try {
                name.charAt(index + 1);
                return true;
            } catch (IndexOutOfBoundsException ignored) {
            }
        }
        return false;
    }

    /**
     * Check if the file has an extension delegating to {@link #hasFileExtension(String)}
     * and using as String parameter the file name.
     * <p>
     *
     * @param file File to check for extension from.
     * @return {@code true} if the file has an extension, otherwise {@code false}
     * @see #hasFileExtension(String)
     */
    public static boolean hasFileExtension(File file) {
        Preconditions.parameterNotNull(file, "file");
        return hasFileExtension(file.getName());
    }

    /**
     * Check if the file has an extension delegating to {@link #hasFileExtension(String)}
     * and using as String parameter the file name.
     * <p>
     *
     * @param file File to check for extension from.
     * @return {@code true} if the file has an extension, otherwise {@code false}
     * @see #hasFileExtension(String)
     */
    public static boolean hasFileExtension(Path file) {
        Preconditions.parameterNotNull(file, "file");
        return hasFileExtension(file.toFile());
    }


    /**
     * Retrieve file name without extension by end reading name
     * until index calculated by this formula {@code (name length - extension length) - 1}
     * is reached.
     * <p>
     * <b>{@code If the file doesn't contain an extension, return an empty string.}</b>
     * </p>
     *
     * <blockquote><pre>
     * getFileNameWithoutExtension("config.yml") returns "config"
     * getFileNameWithoutExtension("messages.json") returns "messages"
     * getFileNameWithoutExtension("graphics.") returns "graphics"
     * </pre></blockquote>
     *
     * @param name File name to get extension from.
     * @return File name without {@code .extension}
     * @see #getFileNameWithoutExtension(File)
     */
    public static String getFileNameWithoutExtension(String name) {
        Preconditions.parameterNotNull(name, "name");
        Optional<FileExtension> extension = getExtension(name);

        return extension.map(
                ext -> {
                    String extName = ext.get();
                    int endIndex = (name.length() - extName.length());
                    return name.substring(0, endIndex);
                }
        ).orElse(name);
    }

    /**
     * Retrieve file name without extension delegating to {@link #getFileNameWithoutExtension(String)}
     * using file name as String parameter.
     *
     * @param file File to get name from.
     * @return File name without {@code .extension}
     * @see #getFileNameWithoutExtension(String)
     */
    public static String getFileNameWithoutExtension(File file) {
        return getFileNameWithoutExtension(file.getName());
    }

    /**
     * Retrieve file name without extension delegating to {@link #getFileNameWithoutExtension(String)}
     * using file name as String parameter.
     *
     * @param file File to get name from.
     * @return File name without {@code .extension}
     * @see #getFileNameWithoutExtension(String)
     */
    public static String getFileNameWithoutExtension(Path file) {
        Preconditions.parameterNotNull(file, "file");
        return getFileNameWithoutExtension(file.toFile());
    }

    /**
     * Retrieve the extension file if there is any.
     *
     * @param fileName File name to get extension.
     * @return If an extension is found, return extension otherwise an empty optional.
     */
    public static Optional<FileExtension> getExtension(String fileName) {
        Preconditions.parameterNotNull(fileName, "fileName");
        if (!hasFileExtension(fileName)) {
            // No extension found.
            return Optional.empty();
        }
        int index = fileName.lastIndexOf('.');
        String extension = fileName.substring(index);
        return Optional.of(new FileExtension(extension));
    }

    /**
     * Retrieve the extension file by getting file name
     * e delegate to {@link #getExtension(String)}
     *
     * @param file File to get extension.
     * @return Extension got (without {@code .} at start)
     */
    public static Optional<FileExtension> getExtension(File file) {
        Preconditions.parameterNotNull(file, "file");
        return getExtension(file.getName());
    }

    /**
     * Retrieve the extension file by getting file name
     * e delegate to {@link #getExtension(String)}
     *
     * @param file File to get extension.
     * @return Extension got (without {@code .} at start)
     */
    public static Optional<FileExtension> getExtension(Path file) {
        Preconditions.parameterNotNull(file, "file");
        return getExtension(file.toFile());
    }


    /**
     * Get an instance of {@link File} from a String correctly
     * respecting directories and file name.
     *
     * @param path Path to file.
     * @return A new instance of {@link File}.
     * @see #getPathFromString(String)
     */
    public static File getFileFromString(String path) {
        Preconditions.parameterNotNull(path, "path");
        return new File(OSUtility.validatePath(path));
    }

    /**
     * Got an instance of {@link File} from a String correctly
     * respecting directories and file name by delegating to {@link #getFileFromString(String)}
     * and calling {@link File#toPath()} method on the result.
     *
     * @param path Path to file.
     * @return A new instance of {@link File}.
     * @see #getPathFromString(String)
     */
    public static Path getPathFromString(String path) {
        Preconditions.parameterNotNull(path, "path");
        return getFileFromString(path).toPath();
    }

    /**
     * Get a formatted file path as String.
     *
     * @param file File to get the path from.
     * @return the formatted file path.
     */
    public static String getStringPathFromFile(File file) {
        Preconditions.parameterNotNull(file, "file");
        return OSUtility.validatePath(file.toString());
    }

    /**
     * Get a formatted file path as String.
     *
     * @param path Path to get the path from.
     * @return the formatted file path.
     */
    public static String getStringPathFromPath(Path path) {
        Preconditions.parameterNotNull(path, "path");
        return getStringPathFromFile(path.toFile());
    }


    /**
     * Checks if the provided input stream is a jar file.
     *
     * @param is The input stream to check for.
     * @return {@code true} if the file is jar, otherwise {@code false}.
     */
    public static boolean isJar(InputStream is) {
        Preconditions.parameterNotNull(is, "is");
        return (hasMagicNumber(is, new byte[]{0x50, 0x4B, 0x03, 0x04})
                || hasMagicNumber(is, new byte[]{0x50, 0x4B, 0x05, 0x06})
                || hasMagicNumber(is, new byte[]{0x50, 0x4B, 0x07, 0x08}));
    }

    /**
     * Checks if the provided file is a jar file.
     *
     * @param file The file to check for.
     * @return {@code true} if the file is jar, otherwise {@code false}.
     */
    public static boolean isJar(File file) {
        Preconditions.parameterNotNull(file, "file");
        return file.isFile()
                && getExtension(file).map(ext -> ext.is("jar")).orElse(false)
                && (hasMagicNumber(file, new byte[]{0x50, 0x4B, 0x03, 0x04})
                || hasMagicNumber(file, new byte[]{0x50, 0x4B, 0x05, 0x06})
                || hasMagicNumber(file, new byte[]{0x50, 0x4B, 0x07, 0x08}));
    }

    /**
     * Checks if the provided path is a jar path.
     *
     * @param path The path to check for.
     * @return {@code true} if the path is jar, otherwise {@code false}.
     */
    public static boolean isJar(Path path) {
        Preconditions.parameterNotNull(path, "path");
        return Files.isRegularFile(path)
                && getExtension(path).map(ext -> ext.is("jar")).orElse(false)
                && (hasMagicNumber(path, new byte[]{0x50, 0x4B, 0x03, 0x04})
                || hasMagicNumber(path, new byte[]{0x50, 0x4B, 0x05, 0x06})
                || hasMagicNumber(path, new byte[]{0x50, 0x4B, 0x07, 0x08}));
    }

    /**
     * Checks if the provided input stream is a zip file.
     *
     * @param is The input stream to check for.
     * @return {@code true} if the file is zip, otherwise {@code false}.
     */
    public static boolean isZip(InputStream is) {
        Preconditions.parameterNotNull(is, "is");
        return (hasMagicNumber(is, new byte[]{0x50, 0x4B, 0x03, 0x04})
                || hasMagicNumber(is, new byte[]{0x50, 0x4B, 0x05, 0x06})
                || hasMagicNumber(is, new byte[]{0x50, 0x4B, 0x07, 0x08}));
    }

    /**
     * Checks if the provided file is a zip file.
     *
     * @param file The file to check for.
     * @return {@code true} if the file is zip, otherwise {@code false}.
     */
    public static boolean isZip(File file) {
        Preconditions.parameterNotNull(file, "file");
        return file.isFile()
                && getExtension(file).map(ext -> ext.is("zip")).orElse(false)
                && (hasMagicNumber(file, new byte[]{0x50, 0x4B, 0x03, 0x04})
                || hasMagicNumber(file, new byte[]{0x50, 0x4B, 0x05, 0x06})
                || hasMagicNumber(file, new byte[]{0x50, 0x4B, 0x07, 0x08}));
    }

    /**
     * Checks if the provided file is a zip file.
     *
     * @param path The path to check for.
     * @return {@code true} if the file is zip, otherwise {@code false}.
     */
    public static boolean isZip(Path path) {
        Preconditions.parameterNotNull(path, "path");
        return Files.isRegularFile(path)
                && getExtension(path).map(ext -> ext.is("zip")).orElse(false)
                && (hasMagicNumber(path, new byte[]{0x50, 0x4B, 0x03, 0x04})
                || hasMagicNumber(path, new byte[]{0x50, 0x4B, 0x05, 0x06})
                || hasMagicNumber(path, new byte[]{0x50, 0x4B, 0x07, 0x08}));
    }


    /**
     * Checks if the header of the provided path equals to the provided magic numbers.
     *
     * @param path         The path to read the header from.
     * @param magicNumbers The bytes to compare with.
     * @return {@code true} if the header equals with the provided bytes.
     */
    public static boolean hasMagicNumber(Path path, byte[] magicNumbers) {
        Preconditions.parameterNotNull(path, "path");
        Preconditions.parameterNotNull(magicNumbers, "magicNumbers");
        byte[] readBytes = Readers.getBytesPathReader().readOrDefault(path, 0, 32, new byte[0]);
        return matches(readBytes, magicNumbers);
    }

    /**
     * Checks if the header of the provided file equals to the provided magic numbers.
     *
     * @param file         The file to read the header from.
     * @param magicNumbers The bytes to compare with.
     * @return {@code true} if the header equals with the provided bytes.
     */
    public static boolean hasMagicNumber(File file, byte[] magicNumbers) {
        Preconditions.parameterNotNull(file, "file");
        Preconditions.parameterNotNull(magicNumbers, "magicNumbers");

        byte[] readBytes = Readers.getBytesFileReader().readOrDefault(file, 0, 32, new byte[0]);
        return matches(readBytes, magicNumbers);
    }

    /**
     * Checks if the header of the provided input stream equals to the provided magic numbers.
     *
     * @param is           The input stream to read the header from.
     * @param magicNumbers The bytes to compare with.
     * @return {@code true} if the header equals with the provided bytes.
     */
    public static boolean hasMagicNumber(InputStream is, byte[] magicNumbers) {
        Preconditions.parameterNotNull(is, "is");
        Preconditions.parameterNotNull(magicNumbers, "magicNumbers");

        byte[] readBytes = Readers.getBytesStreamReader().readOrDefault(is, 0, 32, new byte[0]);
        return matches(readBytes, magicNumbers);
    }

    public static boolean matches(byte[] header, byte[] magic) {
        Preconditions.parameterNotNull(header, "header");
        Preconditions.parameterNotNull(magic, "magic");

        if (header.length < magic.length) return false;
        for (int i = 0; i < magic.length; i++) {
            if (header[i] != magic[i]) return false;
        }
        return true;
    }

}