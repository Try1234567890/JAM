package com.github.trfiles.management.managers.builder;

import com.github.trfiles.management.managers.FileManager;

import java.io.IOException;
import java.nio.file.Path;

class IsWritable implements Action {
    private IsWritable() {}

    private static final class Holder {
        private static final IsWritable INSTANCE = new IsWritable();
    }

    public static IsWritable get() {
        return Holder.INSTANCE;
    }

    @Override
    public boolean check(Path path) {
        return FileManager.isWritable(path);
    }

    @Override
    public IOException getException(Path path) {
        return new IOException("The path at " + path + " is not writable");
    }
}
