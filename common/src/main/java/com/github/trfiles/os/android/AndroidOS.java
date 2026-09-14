package com.github.trfiles.os.android;

import com.github.trfiles.os.OperatingSystem;
import java.nio.file.InvalidPathException;

/**
 * Operating system implementation for Android (Linux Kernel / POSIX).
 */
public class AndroidOS implements OperatingSystem {

    private AndroidOS() {}

    private static final class Holder {
        private static final AndroidOS INSTANCE = new AndroidOS();
    }

    public static AndroidOS get() {
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
        return "Android";
    }

    @Override
    public String validatePath(String path) throws InvalidPathException {
        return AndroidPathValidator.get().validate(path);
    }

    @Override
    public char[] notValidPathSymbols() {
        // Since Android is based on the Linux kernel, the NUL character ('\0')
        // is the only character strictly forbidden inside a path string.
        return new char[]{ '\0' };
    }
}