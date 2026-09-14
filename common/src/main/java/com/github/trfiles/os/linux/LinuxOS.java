package com.github.trfiles.os.linux;

import com.github.trfiles.os.OperatingSystem;
import java.nio.file.InvalidPathException;

public class LinuxOS implements OperatingSystem {
    private LinuxOS() {}

    private static final class Holder {
        private static final LinuxOS INSTANCE = new LinuxOS();
    }

    public static LinuxOS get() {
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
        return "Linux";
    }

    @Override
    public String validatePath(String path) throws InvalidPathException {
        return LinuxPathValidator.get().validate(path);
    }

    @Override
    public char[] notValidPathSymbols() {
        // In POSIX/Linux file systems, the NUL character ('\0') is the only character
        // strictly forbidden inside a path string. The forward slash '/' is a valid separator.
        return new char[]{ '\0' };
    }
}