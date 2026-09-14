package com.github.trfiles.management.managers.builder;

import java.io.IOException;
import java.nio.file.Path;

interface Action {

    boolean check(Path path);

    IOException getException(Path path);

}
