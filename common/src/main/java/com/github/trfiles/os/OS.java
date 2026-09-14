package com.github.trfiles.os;

import java.nio.file.InvalidPathException;

public final class OS {
    private OS() {
    }

    private static final OperatingSystem SYSTEM = OperatingSystemFactory.currentOS();

    public static String pathSeparator() {
        return SYSTEM.pathSeparator();
    }

    public static String lineSeparator() {
        return SYSTEM.lineSeparator();
    }

    public static String name() {
        return SYSTEM.name();
    }

    public static String validatePath(String path) throws InvalidPathException {
        return SYSTEM.validatePath(path);
    }

    public static char[] notValidPathSymbols() {
        return SYSTEM.notValidPathSymbols();
    }

}
