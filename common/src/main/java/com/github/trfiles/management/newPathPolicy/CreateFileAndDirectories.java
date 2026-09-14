package com.github.trfiles.management.newPathPolicy;

import com.github.trfiles.management.FileCreator;
import com.github.trfiles.utility.ValueError;

import java.nio.file.Path;

public class CreateFileAndDirectories extends NewPathPolicyWithDirectories {

    public CreateFileAndDirectories(Path path) {
        super(path);
    }

    @Override
    protected ValueError<Void> newFile(Path path) {
        return new CreateOnlyFile(path).run();
    }
}
