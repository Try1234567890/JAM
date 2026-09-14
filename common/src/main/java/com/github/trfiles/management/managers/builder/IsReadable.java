package com.github.trfiles.management.managers.builder;

import com.github.trfiles.management.managers.FileManager;

import java.io.IOException;
import java.nio.file.Path;

class IsReadable implements Action {
    private IsReadable() {}

    private static final class Holder {
        private static final IsReadable INSTANCE = new IsReadable();
    }

    public static IsReadable get() {
        return Holder.INSTANCE;
    }

    @Override
    public boolean check(Path path) {
        return FileManager.isReadable(path);
    }

    @Override
    public IOException getException(Path path) {
        return new IOException("The path at " + path + " is not readable");
    }
}
