package com.github.trfiles.management.managers.builder;

import com.github.trfiles.management.managers.FileManager;

import java.io.IOException;
import java.nio.file.Path;

class IsFile implements Action {
    private IsFile() {}

    private static final class Holder {
        private static final IsFile INSTANCE = new IsFile();
    }

    public static IsFile get() {
        return Holder.INSTANCE;
    }

    @Override
    public boolean check(Path path) {
        return FileManager.isRegularFile(path);
    }

    @Override
    public IOException getException(Path path) {
        return new IOException("The path at " + path + " is not a file");
    }
}
