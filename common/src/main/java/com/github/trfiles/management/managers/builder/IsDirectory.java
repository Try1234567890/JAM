package com.github.trfiles.management.managers.builder;

import com.github.trfiles.management.managers.FileManager;

import java.io.IOException;
import java.nio.file.Path;

class IsDirectory implements Action {

    private IsDirectory() {}

    private static final class Holder {
        private static final IsDirectory INSTANCE = new IsDirectory();
    }

    public static IsDirectory get() {
        return Holder.INSTANCE;
    }

    @Override
    public boolean check(Path path) {
        return FileManager.isDirectory(path);
    }

    @Override
    public IOException getException(Path path) {
        return new IOException("The path at " + path + " is not a directory");
    }
}
