package com.github.trfiles.memory.systems;


import com.github.trfiles.exceptions.UnexpectedValueType;

import java.util.Optional;

public class ValueEntry<T> {
    private final T value;
    private final T def;
    private final boolean isPresent;

    public ValueEntry(T value, T def, boolean isPresent) {
        this.value = value;
        this.def = def;
        this.isPresent = isPresent;
    }

    public ValueEntry(T value, T def) {
        this.value = value;
        this.def = def;
        this.isPresent = value != null;
    }

    public boolean isPresent() {
        return isPresent;
    }

    public T getRaw() {
        return value;
    }

    public Optional<T> get() {
        return Optional.ofNullable(isPresent() ? value : def);
    }

    public T getOrNull() {
        return get().orElse(null);
    }

    public T getOrThrown(Throwable throwable) {
        return get().orElseThrow(() -> throwable instanceof UnexpectedValueType uvt ? uvt : new UnexpectedValueType(throwable));
    }

    public T def() {
        return def;
    }

    @Override
    public String toString() {
        if (!isPresent) return "[NONE]";
        else if (value != null) return value.toString();
        else if (def != null) return def.toString();
        else return "[NONE]";
    }
}
