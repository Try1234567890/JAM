package com.github.trfiles.utility;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;

public class ThrowableCollector extends HashSet<Throwable> {

    /**
     * <b>Doesn't nothing.</b>
     */
    @Override
    public boolean remove(Object o) {
        return false;
    }

    /**
     * <b>Doesn't nothing.</b>
     */
    @Override
    public boolean removeIf(@NotNull Predicate<? super Throwable> filter) {
        return false;
    }

    /**
     * <b>Doesn't nothing.</b>
     */
    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    /**
     * <b>Doesn't nothing.</b>
     */
    @Override
    public void clear() {

    }

    public <T extends Throwable> Set<T> as(Function<Throwable, T> converter) {
        Set<T> throwables = new HashSet<>();

        for (Throwable throwable : this) {
            T newThrowable = converter.apply(throwable);
            throwables.add(newThrowable);
        }

        return throwables;
    }

    public Set<IOException> asIOE() {
        return as((t) -> t instanceof IOException e ? e : new IOException(t));
    }

    public Set<NullPointerException> asNPE() {
        return as((t) -> t instanceof NullPointerException e ? e : new NullPointerException(t.getMessage()));
    }

    public Set<IllegalArgumentException> asIAE() {
        return as((t) -> t instanceof IllegalArgumentException e ? e : new IllegalArgumentException(t));
    }
}
