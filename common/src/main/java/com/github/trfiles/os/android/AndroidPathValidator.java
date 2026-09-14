package com.github.trfiles.os.android;

import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Singleton validator and resolver for Android file paths.
 */
class AndroidPathValidator {

    private AndroidPathValidator() {}

    private static final class Holder {
        private static final AndroidPathValidator INSTANCE = new AndroidPathValidator();
    }

    public static AndroidPathValidator get() {
        return Holder.INSTANCE;
    }

    private AndroidOS android() {
        return AndroidOS.get();
    }

    /**
     * Validates an Android path string and resolves any relative path format 
     * into a fully qualified, normalized absolute path.
     *
     * @param path The raw path string to validate and resolve.
     * @return The normalized, absolute path string.
     * @throws InvalidPathException If the path is null, empty, or contains forbidden characters.
     */
    public String validate(String path) throws InvalidPathException {
        if (path == null || path.trim().isEmpty()) {
            throw new InvalidPathException("", "Path cannot be null or empty.");
        }

        // 1. Inspect raw string for forbidden path symbols (e.g., '\0')
        checkForForbiddenSymbols(path);

        try {
            // 2. Delegate path resolution to Java NIO.
            //    toAbsolutePath() expands relative paths against the CWD,
            //    and normalize() cleans up redundant '.' and '..' segments.
            Path pathObj = Paths.get(path);
            Path resolvedPath = pathObj.toAbsolutePath().normalize();

            return resolvedPath.toString();

        } catch (InvalidPathException e) {
            throw e;
        } catch (Exception e) {
            throw new InvalidPathException(path, "Failed to resolve Android path: " + e.getMessage());
        }
    }

    /**
     * Scans the full path string for forbidden symbols provided by AndroidOS.
     *
     * @param path The path string to evaluate.
     */
    private void checkForForbiddenSymbols(String path) {
        for (int i = 0; i < path.length(); i++) {
            char c = path.charAt(i);
            for (char invalid : android().notValidPathSymbols()) {
                if (c == invalid) {
                    throw new InvalidPathException(
                        path, 
                        "The character '\\0' (NUL) is forbidden in Android paths.", 
                        i
                    );
                }
            }
        }
    }
}