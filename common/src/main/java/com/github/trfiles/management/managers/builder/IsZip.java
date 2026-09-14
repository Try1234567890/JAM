package com.github.trfiles.management.managers.builder;

import com.github.trfiles.management.FileUtility;

import java.io.IOException;
import java.nio.file.Path;

class IsZip implements Action {
    private IsZip() {}

    private static final class Holder {
        private static final IsZip INSTANCE = new IsZip();
    }

    public static IsZip get() {
        return Holder.INSTANCE;
    }

    @Override
    public boolean check(Path path) {
        try {
            return FileUtility.isZip(path);
        } catch (IOException e) {
            return false;
        }
    }

    @Override
    public IOException getException(Path path) {
        return new IOException("The path at " + path + " is not a .zip archive");
    }
}
