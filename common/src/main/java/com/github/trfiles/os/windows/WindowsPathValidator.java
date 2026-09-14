package com.github.trfiles.os.windows;

import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Utility class for validating and resolving Windows file system paths.
 */
class WindowsPathValidator {

    private WindowsPathValidator() {}

    private static final class Holder {
        private static final WindowsPathValidator INSTANCE = new WindowsPathValidator();
    }

    public static WindowsPathValidator get() {
        return Holder.INSTANCE;
    }

    private WindowsOS windows() {
        return WindowsOS.get();
    }

    /**
     * Validates a Windows path string and resolves drive-relative forms
     * (e.g., "C:Projects\file.txt") into a fully qualified absolute path.
     *
     * @param path the raw path string to validate
     * @return the normalized absolute path string
     * @throws InvalidPathException if the path contains illegal characters or is null/empty
     */
    public String validate(String path) throws InvalidPathException {
        if (path == null || path.trim().isEmpty()) {
            throw new InvalidPathException("", "Path cannot be null or empty");
        }

        // 1. Verify that the path contains no OS-forbidden characters (skipping drive colon)
        checkForForbiddenSymbols(path);

        try {
            // 2. Wrap string into a Java NIO Path object
            Path pathObj = Paths.get(path);

            // 3. toAbsolutePath() delegates to Windows native API (GetFullPathName),
            // correctly expanding drive-relative paths (e.g., "C:Projects" -> "C:\CurrentDir\Projects").
            // normalize() removes redundant path elements like "." or ".."
            Path resolvedPath = pathObj.toAbsolutePath().normalize();

            return resolvedPath.toString();

        } catch (InvalidPathException e) {
            throw e;
        } catch (Exception e) {
            throw new InvalidPathException(path, "Failed to resolve path: " + e.getMessage());
        }
    }

    /**
     * Inspects the path string for illegal characters defined by the target OS.
     * Skips the drive letter specification (e.g., "C:") to allow valid colons.
     *
     * @param path the path string to check
     */
    private void checkForForbiddenSymbols(String path) {
        int startIndex = 0;

        // Skip drive letter prefix if present (e.g., "C:")
        if (path.length() >= 2 && Character.isLetter(path.charAt(0)) && path.charAt(1) == ':') {
            startIndex = 2;
        }

        // Scan the remainder of the path for forbidden characters
        for (int i = startIndex; i < path.length(); i++) {
            char c = path.charAt(i);
            for (char invalid : windows().notValidPathSymbols()) {
                if (c == invalid) {
                    throw new InvalidPathException(path, "The character '" + invalid + "' is forbidden in paths.", i);
                }
            }
        }
    }
}