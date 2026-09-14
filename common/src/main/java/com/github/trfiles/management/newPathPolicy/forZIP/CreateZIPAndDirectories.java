package com.github.trfiles.management.newPathPolicy.forZIP;

import com.github.trfiles.management.FileCreator;
import com.github.trfiles.management.newPathPolicy.NewPathPolicyWithDirectories;
import com.github.trfiles.utility.ValueError;

import java.nio.file.Path;

public class CreateZIPAndDirectories extends NewPathPolicyWithDirectories implements NewZIPPolicy {
    public CreateZIPAndDirectories(Path path) {
        super(path);
    }

    @Override
    protected ValueError<Void> newFile(Path path) {
        return toValueError(FileCreator.newZIP(path));
    }
}