package com.github.trfiles.management.managers.builder;

import com.github.trfiles.management.managers.FileManager;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.function.Supplier;

public final class BuilderFileManager {
    private final Set<Action> actions = new LinkedHashSet<>();


    public BuilderFileManager isReadable() {
        actions.add(IsReadable.get());
        return this;
    }

    public BuilderFileManager isWritable() {
        actions.add(IsWritable.get());
        return this;
    }

    public BuilderFileManager isExecutable() {
        actions.add(IsExecutable.get());
        return this;
    }

    public BuilderFileManager exists() {
        actions.add(Exists.get());
        return this;
    }

    public BuilderFileManager isDirectory() {
        actions.add(IsDirectory.get());
        return this;
    }

    public BuilderFileManager isRegularFile() {
        actions.add(IsFile.get());
        return this;
    }

    public BuilderFileManager isJAR() {
        actions.add(IsJar.get());
        return this;
    }

    public BuilderFileManager isZIP() {
        actions.add(IsZip.get());
        return this;
    }

    public boolean check(Path path) {
        for (Action action : actions) {
            if (!action.check(path)) return false;
        }
        return true;
    }
    public boolean check(File file) {
        return check(file.toPath());
    }
    public boolean check(String path) {
        return check(Paths.get(path));
    }

    public boolean test(Path path) throws IOException {
        for (Action action : actions) {
            if (!action.check(path)) {
                throw action.getException(path);
            }
        }
        return true;
    }
    public boolean test(File file) throws IOException {
        return test(file.toPath());
    }
    public boolean test(String path) throws IOException {
        return test(Paths.get(path));
    }

    public <E extends Throwable> boolean testWith(Path path, Supplier<E> ex) throws E {
        for (Action action : actions) {
            if (!action.check(path)) {
                E exception = ex.get();

                if (exception.getCause() == null) exception.initCause(action.getException(path));
                else exception.addSuppressed(action.getException(path));
                throw exception;
            }
        }
        return true;
    }
    public <E extends Throwable> boolean testWith(File file, Supplier<E> ex) throws E {
        return testWith(file.toPath(), ex);
    }
    public <E extends Throwable> boolean testWith(String path, Supplier<E> ex) throws E {
        return testWith(Paths.get(path), ex);
    }
}
