package com.github.trfiles.management.io.readers;

import java.util.Optional;
import java.util.function.Supplier;

public interface Reader<I, O> {

    static ReaderBuilder builder() {
        return ReaderBuilder.get();
    }

    O read(I input, long offset, long length) throws Exception;

    O read(I input, long offset) throws Exception;

    O readUntil(I input, long length) throws Exception;

    O read(I input) throws Exception;

    default Optional<O> readSilently(I input, long offset, long length) {
        try {
            O res = read(input, offset, length);
            return Optional.ofNullable(res);
        } catch (Exception _) {
            return Optional.empty();
        }
    }

    default Optional<O> readSilently(I input, long offset) {
        try {
            O res = read(input, offset);
            return Optional.ofNullable(res);
        } catch (Exception _) {
            return Optional.empty();
        }

    }

    default Optional<O> readUntilSilently(I input, long length) {
        try {
            O res = readUntil(input, length);
            return Optional.ofNullable(res);
        } catch (Exception _) {
            return Optional.empty();
        }

    }

    default Optional<O> readSilently(I input) {
        try {
            O res = read(input);
            return Optional.ofNullable(res);
        } catch (Exception _) {
            return Optional.empty();
        }
    }

    default O readGet(I input, long offset, long length, Supplier<O> def) {
        try {
            return read(input, offset, length);
        } catch (Exception _) {
            return def.get();
        }
    }

    default O readGet(I input, long offset, Supplier<O> def) {
        try {
            return read(input, offset);
        } catch (Exception _) {
            return def.get();
        }

    }

    default O readUntilGet(I input, long length, Supplier<O> def) {
        try {
            return readUntil(input, length);
        } catch (Exception _) {
            return def.get();
        }

    }

    default O readGet(I input, Supplier<O> def) {
        try {
            return read(input);
        } catch (Exception _) {
            return def.get();
        }
    }


    default O readSafe(I input, long offset, long length, O def) {
        try {
            return read(input, offset, length);
        } catch (Exception _) {
            return def;
        }
    }

    default O readSafe(I input, long offset, O def) {
        try {
            return read(input, offset);
        } catch (Exception _) {
            return def;
        }

    }

    default O readUntilSafe(I input, long length, O def) {
        try {
            return readUntil(input, length);
        } catch (Exception _) {
            return def;
        }

    }

    default O readSafe(I input, O def) {
        try {
            return read(input);
        } catch (Exception _) {
            return def;
        }
    }
}
