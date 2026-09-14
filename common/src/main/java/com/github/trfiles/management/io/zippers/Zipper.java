package com.github.trfiles.management.io.zippers;

import java.nio.file.Path;
import java.util.function.Predicate;

public interface Zipper<I, D> {

    void zip(I input, D destination, Predicate<Path> predicate) throws Exception;

    void zip(I input, D destination) throws Exception;

}
