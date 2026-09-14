package com.github.trfiles.os.windows;

import com.github.trfiles.os.OperatingSystem;

import java.nio.file.InvalidPathException;

public class WindowsOS implements OperatingSystem {
    private WindowsOS() {
    }

    private static final class Holder {
        private static final WindowsOS INSTANCE = new WindowsOS();
    }

    public static WindowsOS get() {
        return Holder.INSTANCE;
    }

    @Override
    public String pathSeparator() {
        return "\\";
    }

    @Override
    public String lineSeparator() {
        return "\n\r";
    }

    @Override
    public String name() {
        return "Windows";
    }

    @Override
    public char[] notValidPathSymbols() {
        return new char[]{
                ':', '*', '?', '"', '<', '>', '|'
        };
    }

    @Override
    public String validatePath(String path) {
        return WindowsPathValidator.get().validate(toBackSlash(path));
    }

    private String toBackSlash(String path) {
        return path.replace("/", "\\");
    }

}








