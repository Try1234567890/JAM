package com.github.trfiles.management.managers.builder;

import com.github.trfiles.management.managers.FileManager;

import java.io.IOException;
import java.nio.file.Path;

class Exists implements Action {
    private Exists() {}

    private static final class Holder {
        private static final Exists INSTANCE = new Exists();
    }

    public static Exists get() {
        return Holder.INSTANCE;
    }

    @Override
    public boolean check(Path path) {
        return FileManager.exists(path);
    }

    @Override
    public IOException getException(Path path) {
        return new IOException("The path at " + path + " not exists");
    }
}
