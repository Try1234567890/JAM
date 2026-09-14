package com.github.trfiles.management.rollbackable;

import com.github.trfiles.utility.ValueError;

public interface Rollbackable {

    ValueError<Void> run();

    ValueError<Void> undo();

    ValueError<Void> redo();

    boolean wasUndid();
}
