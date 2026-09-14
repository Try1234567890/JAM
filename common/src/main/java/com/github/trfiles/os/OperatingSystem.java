package com.github.trfiles.os;

import java.nio.file.InvalidPathException;

public interface OperatingSystem {

    String pathSeparator();

    String lineSeparator();

    String name();

    String validatePath(String path) throws InvalidPathException;

    char[] notValidPathSymbols();
}
