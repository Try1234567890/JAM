package com.github.trfiles.os.mac;

import com.github.trfiles.os.OperatingSystem;
import java.nio.file.InvalidPathException;

/**
 * Operating system implementation for macOS (Darwin / POSIX).
 */
public class MacOS implements OperatingSystem {

    private MacOS() {}

    private static final class Holder {
        private static final MacOS INSTANCE = new MacOS();
    }

    public static MacOS get() {
        return Holder.INSTANCE;
    }

    @Override
    public String pathSeparator() {
        return "/";
    }

    @Override
    public String lineSeparator() {
        return "\n";
    }

    @Override
    public String name() {
        return "MacOS";
    }

    @Override
    public String validatePath(String path) throws InvalidPathException {
        return MacOSPathValidator.get().validate(path);
    }

    @Override
    public char[] notValidPathSymbols() {
        // In macOS (APFS/HFS+ file systems), only the NUL character ('\0') is strictly
        // forbidden inside a path string. Forward slashes '/' are valid path separators.
        return new char[]{ '\0' };
    }
}