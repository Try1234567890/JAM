package com.github.trfiles.management.newPathPolicy;

import com.github.trfiles.management.FileCreator;
import com.github.trfiles.utility.ValueError;
import com.github.utilities.validators.Preconditions;

import java.nio.file.Path;

public class CreateOnlyFile implements NewPathPolicy {
    private final Path path;
    private boolean undid = false;

    public CreateOnlyFile(Path path) {
        this.path = Preconditions.simpleParameterNotNull(path, "path").toAbsolutePath();
    }

    @Override
    public boolean wasUndid() {
        return undid;
    }

    @Override
    public ValueError<Void> run() {
        return toValueError(FileCreator.newSimpleFile(path));
    }

    @Override
    public ValueError<Void> undo() {
        ValueError<Void> res = toValueError(FileCreator.delete(path));
        if (res.isSuccess())
            undid = true;
        return res;
    }

    @Override
    public ValueError<Void> redo() {
        if (!undid) return ValueError.error(new IllegalStateException("Cannot redo the operation if was not undid."));
        return run();
    }
}
