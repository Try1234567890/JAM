package com.github.trfiles.management.io.writers;

import com.github.trfiles.utility.TriConsumer;
import com.github.utilities.options.BooleanOption;
import com.github.utilities.options.SetOption;
import com.github.utilities.validators.Preconditions;

import java.util.HashSet;
import java.util.Set;
import java.util.function.BiConsumer;

public class WriterConfiguration<V, O> {
    /**
     * <b>INTERNAL OPTION! THIS MUST NOT BE ACCESSIBLE FROM EXTERNAL SOURCES</b>
     *
     */
    protected final BooleanOption SHOULD_CHECK_INDEXES = new BooleanOption(true);


    private final BooleanOption SAVE_EXCEPTIONS = new BooleanOption(true);
    private final SetOption<BiConsumer<V, O>> ON_PRE_PROCESS = new SetOption<>(new HashSet<>());
    private final SetOption<BiConsumer<V, O>> ON_POST_PROCESS = new SetOption<>(new HashSet<>());
    private final SetOption<BiConsumer<V, O>> ON_SUCCESS_PROCESS = new SetOption<>(new HashSet<>());
    private final SetOption<TriConsumer<V, O, Throwable>> ON_FAIL_PROCESS = new SetOption<>(new HashSet<>());

    public WriterConfiguration<V, O> setSaveExceptions(boolean value) {
        SAVE_EXCEPTIONS.set(value);
        return this;
    }

    public WriterConfiguration<V, O> saveExceptions() {
        return setSaveExceptions(true);
    }

    public WriterConfiguration<V, O> notSaveExceptions() {
        return setSaveExceptions(false);
    }

    public WriterConfiguration<V, O> toggleSaveExceptions() {
        return setSaveExceptions(!shouldSaveExceptions());
    }

    public boolean shouldSaveExceptions() {
        return Preconditions.simpleNotNull(SAVE_EXCEPTIONS.get(), true);
    }

    public WriterConfiguration<V, O> newOnPreProcess(Set<BiConsumer<V, O>> onPreProcess) {
        ON_PRE_PROCESS.addAll(Preconditions.simpleParameterNotNull(onPreProcess, "onPreProcess"));
        return this;
    }

    public WriterConfiguration<V, O> newOnPreProcess(BiConsumer<V, O> onPreProcess) {
        ON_PRE_PROCESS.add(Preconditions.simpleParameterNotNull(onPreProcess, "onPreProcess"));
        return this;
    }

    public Set<BiConsumer<V, O>> getOnPreProcess() {
        return new HashSet<>(ON_PRE_PROCESS.get());
    }

    public WriterConfiguration<V, O> newOnPostProcess(Set<BiConsumer<V, O>> onPostProcess) {
        ON_POST_PROCESS.addAll(Preconditions.simpleParameterNotNull(onPostProcess, "onPostProcess"));
        return this;
    }

    public WriterConfiguration<V, O> newOnPostProcess(BiConsumer<V, O> onPostProcess) {
        ON_POST_PROCESS.add(Preconditions.simpleParameterNotNull(onPostProcess, "onPostProcess"));
        return this;
    }

    public Set<BiConsumer<V, O>> getOnPostProcess() {
        return new HashSet<>(ON_POST_PROCESS.get());
    }

    public WriterConfiguration<V, O> newOnFailProcess(Set<TriConsumer<V, O, Throwable>> onFailProcess) {
        ON_FAIL_PROCESS.addAll(Preconditions.simpleParameterNotNull(onFailProcess, "onFailProcess"));
        return this;
    }

    public WriterConfiguration<V, O> newOnFailProcess(TriConsumer<V, O, Throwable> onFailProcess) {
        ON_FAIL_PROCESS.add(Preconditions.simpleParameterNotNull(onFailProcess, "onFailProcess"));
        return this;
    }

    public Set<TriConsumer<V, O, Throwable>> getOnFailProcess() {
        return new HashSet<>(ON_FAIL_PROCESS.get());
    }

    public WriterConfiguration<V, O> newOnSuccessProcess(Set<BiConsumer<V, O>> onSuccessProcess) {
        ON_SUCCESS_PROCESS.addAll(Preconditions.simpleParameterNotNull(onSuccessProcess, "onSuccessProcess"));
        return this;
    }

    public WriterConfiguration<V, O> newOnSuccessProcess(BiConsumer<V, O> onSuccessProcess) {
        ON_SUCCESS_PROCESS.add(Preconditions.simpleParameterNotNull(onSuccessProcess, "onSuccessProcess"));
        return this;
    }

    public Set<BiConsumer<V, O>> getOnSuccessProcess() {
        return new HashSet<>(ON_SUCCESS_PROCESS.get());
    }

    public WriterConfiguration<V, O> onPreProcess(BiConsumer<V, O> task) {
        ON_PRE_PROCESS.add(Preconditions.simpleParameterNotNull(task, "task"));
        return this;
    }

}