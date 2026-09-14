package com.github.trfiles.utility;

import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

public record ValueError<T>(@Nullable T value, @Nullable Throwable error) {

    private static final ValueError<?> EMPTY = new ValueError<>(null, null);

    @SuppressWarnings("unchecked")
    public static <T> ValueError<T> empty() {
        return (ValueError<T>) EMPTY;
    }

    public static <T> ValueError<T> success(T value) {
        return new ValueError<>(value, null);
    }

    public static <T> ValueError<T> error(Throwable error) {
        return new ValueError<>(null, error);
    }

    public static <T> ValueError<T> monitor(Supplier<T> process) {
        try {
            T value = process.get();
            return ValueError.success(value);
        } catch (Throwable t) {
            return ValueError.error(t);
        }
    }

    public boolean isSuccess() {
        return error == null;
    }

    public boolean isError() {
        return value == null && error != null;
    }
}
