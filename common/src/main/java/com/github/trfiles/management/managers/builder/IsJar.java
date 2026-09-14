package com.github.trfiles.management.managers.builder;

import com.github.trfiles.management.FileUtility;

import java.io.IOException;
import java.nio.file.Path;

class IsJar implements Action {
    private IsJar() {}

    private static final class Holder {
        private static final IsJar INSTANCE = new IsJar();
    }

    public static IsJar get() {
        return Holder.INSTANCE;
    }

    @Override
    public boolean check(Path path) {
        try {
            return FileUtility.isJar(path);
        } catch (IOException e) {
            return false;
        }
    }

    @Override
    public IOException getException(Path path) {
        return new IOException("The path at " + path + " is not a .jar archive");
    }
}
