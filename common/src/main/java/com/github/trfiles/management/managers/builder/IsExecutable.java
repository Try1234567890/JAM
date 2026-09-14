package com.github.trfiles.management.managers.builder;

import com.github.trfiles.management.managers.FileManager;

import java.io.IOException;
import java.nio.file.Path;

class IsExecutable implements Action {
    private IsExecutable() {}

    private static final class Holder {
        private static final IsExecutable INSTANCE = new IsExecutable();
    }

    public static IsExecutable get() {
        return Holder.INSTANCE;
    }

    @Override
    public boolean check(Path path) {
        return FileManager.isExecutable(path);
    }

    @Override
    public IOException getException(Path path) {
        return new IOException("The path at " + path + " is not executable");
    }
}
