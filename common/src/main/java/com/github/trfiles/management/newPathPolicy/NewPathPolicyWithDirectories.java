package com.github.trfiles.management.newPathPolicy;

import com.github.trfiles.management.FileCreator;
import com.github.trfiles.utility.ValueError;
import com.github.utilities.validators.Preconditions;

import java.nio.file.Files;
import java.nio.file.Path;

public abstract class NewPathPolicyWithDirectories implements NewPathPolicy {
    private final Path path;
    private final Path root;
    private boolean undid;

    public NewPathPolicyWithDirectories(Path path) {
        this.path = Preconditions.simpleParameterNotNull(path, "path").toAbsolutePath();
        this.root = getFirstExistingParentDirectory(path).toAbsolutePath();
    }

    private static Path getFirstExistingParentDirectory(Path path) {
        Path parent = path.getParent();
        while (parent != null && Files.isDirectory(parent)) {
            parent = parent.getParent();
        }
        return parent == null ? path.getRoot() : parent;
    }

    @Override
    public boolean wasUndid() {
        return undid;
    }

    protected abstract ValueError<Void> newFile(Path path);

    @Override
    public ValueError<Void> run() {
        Path parent = path.getParent();
        if (parent != null) { // We are at the root. Path is a file name only.
            FileCreator.Result directories = FileCreator.newDirectories(parent);
            if (!directories.isSuccess()) return toValueError(directories);
        }
        return newFile(path);
    }

    @Override
    public ValueError<Void> undo() {
        FileCreator.Result rootRes = FileCreator.delete(path);
        if (!rootRes.isSuccess()) return toValueError(rootRes);

        Path parent = path.getParent();
        while (parent != null && !parent.equals(root)) {
            FileCreator.Result res = FileCreator.delete(parent);
            if (!res.isSuccess()) return toValueError(res);
            parent = parent.getParent();
        }

        undid = true;
        return ValueError.success(null);
    }

    @Override
    public ValueError<Void> redo() {
        if (!undid) return ValueError.error(new IllegalStateException("Cannot redo the operation if was not undid."));
        return run();
    }
}
