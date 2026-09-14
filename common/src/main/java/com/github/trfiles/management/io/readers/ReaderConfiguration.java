package com.github.trfiles.management.io.readers;

import com.github.trfiles.utility.TriConsumer;
import com.github.utilities.options.BooleanOption;
import com.github.utilities.options.SetOption;
import com.github.utilities.validators.Preconditions;

import java.util.HashSet;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class ReaderConfiguration<V, O> {
    /**
     * <b>INTERNAL OPTION! THIS MUST NOT BE ACCESSIBLE FROM EXTERNAL SOURCES</b>
     *
     */
    protected final BooleanOption SHOULD_CHECK_INDEXES = new BooleanOption(true);

    private final BooleanOption SAVE_EXCEPTIONS = new BooleanOption(true);
    private final SetOption<Consumer<V>> ON_PRE_PROCESS = new SetOption<>(new HashSet<>());
    private final SetOption<Consumer<V>> ON_POST_PROCESS = new SetOption<>(new HashSet<>());
    private final SetOption<BiConsumer<V, O>> ON_SUCCESS_PROCESS = new SetOption<>(new HashSet<>());
    private final SetOption<BiConsumer<V, Throwable>> ON_FAIL_PROCESS = new SetOption<>(new HashSet<>());

    public ReaderConfiguration<V, O> setSaveExceptions(boolean value) {
        SAVE_EXCEPTIONS.set(value);
        return this;
    }

    public ReaderConfiguration<V, O> saveExceptions() {
        return setSaveExceptions(true);
    }

    public ReaderConfiguration<V, O> notSaveExceptions() {
        return setSaveExceptions(false);
    }

    public ReaderConfiguration<V, O> toggleSaveExceptions() {
        return setSaveExceptions(!shouldSaveExceptions());
    }

    public boolean shouldSaveExceptions() {
        return Preconditions.simpleNotNull(SAVE_EXCEPTIONS.get(), true);
    }

    public ReaderConfiguration<V, O> newOnPreProcess(Set<Consumer<V>> onPreProcess) {
        ON_PRE_PROCESS.addAll(Preconditions.simpleParameterNotNull(onPreProcess, "onPreProcess"));
        return this;
    }

    public ReaderConfiguration<V, O> newOnPreProcess(Consumer<V> onPreProcess) {
        ON_PRE_PROCESS.add(Preconditions.simpleParameterNotNull(onPreProcess, "onPreProcess"));
        return this;
    }

    public Set<Consumer<V>> getOnPreProcess() {
        return new HashSet<>(ON_PRE_PROCESS.get());
    }

    public ReaderConfiguration<V, O> newOnPostProcess(Set<Consumer<V>> onPostProcess) {
        ON_POST_PROCESS.addAll(Preconditions.simpleParameterNotNull(onPostProcess, "onPostProcess"));
        return this;
    }

    public ReaderConfiguration<V, O> newOnPostProcess(Consumer<V> onPostProcess) {
        ON_POST_PROCESS.add(Preconditions.simpleParameterNotNull(onPostProcess, "onPostProcess"));
        return this;
    }

    public Set<Consumer<V>> getOnPostProcess() {
        return new HashSet<>(ON_POST_PROCESS.get());
    }

    public ReaderConfiguration<V, O> newOnFailProcess(Set<BiConsumer<V, Throwable>> onFailProcess) {
        ON_FAIL_PROCESS.addAll(Preconditions.simpleParameterNotNull(onFailProcess, "onFailProcess"));
        return this;
    }

    public ReaderConfiguration<V, O> newOnFailProcess(BiConsumer<V, Throwable> onFailProcess) {
        ON_FAIL_PROCESS.add(Preconditions.simpleParameterNotNull(onFailProcess, "onFailProcess"));
        return this;
    }

    public Set<BiConsumer<V, Throwable>> getOnFailProcess() {
        return new HashSet<>(ON_FAIL_PROCESS.get());
    }

    public ReaderConfiguration<V, O> newOnSuccessProcess(Set<BiConsumer<V, O>> onSuccessProcess) {
        ON_SUCCESS_PROCESS.addAll(Preconditions.simpleParameterNotNull(onSuccessProcess, "onSuccessProcess"));
        return this;
    }

    public ReaderConfiguration<V, O> newOnSuccessProcess(BiConsumer<V, O> onSuccessProcess) {
        ON_SUCCESS_PROCESS.add(Preconditions.simpleParameterNotNull(onSuccessProcess, "onSuccessProcess"));
        return this;
    }

    public Set<BiConsumer<V, O>> getOnSuccessProcess() {
        return new HashSet<>(ON_SUCCESS_PROCESS.get());
    }
}