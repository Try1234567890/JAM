package com.github.trfiles.management.io.streamers;

import com.github.trfiles.utility.TriConsumer;
import com.github.utilities.options.BooleanOption;
import com.github.utilities.options.SetOption;
import com.github.utilities.validators.Preconditions;

import java.util.HashSet;
import java.util.Set;
import java.util.function.BiConsumer;

public class StreamerConfiguration<I, O> {
    /**
     * <b>INTERNAL OPTION! THIS MUST NOT BE ACCESSIBLE FROM EXTERNAL SOURCES</b>
     */
    protected final BooleanOption SHOULD_CHECK_INDEXES = new BooleanOption(true);

    /**
     * The default amount of bytes read from the source, and immediately written to the destination,
     * on every single iteration of the streaming loop.
     */
    public static final int DEFAULT_BUFFER_SIZE = 8192;

    private final BooleanOption SAVE_EXCEPTIONS = new BooleanOption(true);
    private int bufferSize = DEFAULT_BUFFER_SIZE;
    private final SetOption<BiConsumer<I, O>> ON_PRE_PROCESS = new SetOption<>(new HashSet<>());
    private final SetOption<BiConsumer<I, O>> ON_POST_PROCESS = new SetOption<>(new HashSet<>());
    private final SetOption<BiConsumer<I, O>> ON_SUCCESS_PROCESS = new SetOption<>(new HashSet<>());
    private final SetOption<TriConsumer<I, O, Throwable>> ON_FAIL_PROCESS = new SetOption<>(new HashSet<>());

    public StreamerConfiguration<I, O> setSaveExceptions(boolean value) {
        SAVE_EXCEPTIONS.set(value);
        return this;
    }

    public StreamerConfiguration<I, O> saveExceptions() {
        return setSaveExceptions(true);
    }

    public StreamerConfiguration<I, O> notSaveExceptions() {
        return setSaveExceptions(false);
    }

    public StreamerConfiguration<I, O> toggleSaveExceptions() {
        return setSaveExceptions(!shouldSaveExceptions());
    }

    public boolean shouldSaveExceptions() {
        return Preconditions.simpleNotNull(SAVE_EXCEPTIONS.get(), true);
    }

    /**
     * Sets the size, in bytes, of the chunks read from the source and immediately written to the
     * destination on every iteration of the streaming loop. A bigger buffer generally means fewer,
     * heavier I/O operations; a smaller one means more, lighter ones.
     *
     * @param bufferSize The size, in bytes, of a single chunk. Must be greater than 0.
     */
    public StreamerConfiguration<I, O> setBufferSize(int bufferSize) {
        if (bufferSize <= 0) {
            throw new IllegalArgumentException("The \"bufferSize\" must be greater than 0!");
        }
        this.bufferSize = bufferSize;
        return this;
    }

    public int getBufferSize() {
        return bufferSize;
    }

    public StreamerConfiguration<I, O> newOnPreProcess(Set<BiConsumer<I, O>> onPreProcess) {
        ON_PRE_PROCESS.addAll(Preconditions.simpleParameterNotNull(onPreProcess, "onPreProcess"));
        return this;
    }

    public StreamerConfiguration<I, O> newOnPreProcess(BiConsumer<I, O> onPreProcess) {
        ON_PRE_PROCESS.add(Preconditions.simpleParameterNotNull(onPreProcess, "onPreProcess"));
        return this;
    }

    public Set<BiConsumer<I, O>> getOnPreProcess() {
        return new HashSet<>(ON_PRE_PROCESS.get());
    }

    public StreamerConfiguration<I, O> newOnPostProcess(Set<BiConsumer<I, O>> onPostProcess) {
        ON_POST_PROCESS.addAll(Preconditions.simpleParameterNotNull(onPostProcess, "onPostProcess"));
        return this;
    }

    public StreamerConfiguration<I, O> newOnPostProcess(BiConsumer<I, O> onPostProcess) {
        ON_POST_PROCESS.add(Preconditions.simpleParameterNotNull(onPostProcess, "onPostProcess"));
        return this;
    }

    public Set<BiConsumer<I, O>> getOnPostProcess() {
        return new HashSet<>(ON_POST_PROCESS.get());
    }

    public StreamerConfiguration<I, O> newOnFailProcess(Set<TriConsumer<I, O, Throwable>> onFailProcess) {
        ON_FAIL_PROCESS.addAll(Preconditions.simpleParameterNotNull(onFailProcess, "onFailProcess"));
        return this;
    }

    public StreamerConfiguration<I, O> newOnFailProcess(TriConsumer<I, O, Throwable> onFailProcess) {
        ON_FAIL_PROCESS.add(Preconditions.simpleParameterNotNull(onFailProcess, "onFailProcess"));
        return this;
    }

    public Set<TriConsumer<I, O, Throwable>> getOnFailProcess() {
        return new HashSet<>(ON_FAIL_PROCESS.get());
    }

    public StreamerConfiguration<I, O> newOnSuccessProcess(Set<BiConsumer<I, O>> onSuccessProcess) {
        ON_SUCCESS_PROCESS.addAll(Preconditions.simpleParameterNotNull(onSuccessProcess, "onSuccessProcess"));
        return this;
    }

    public StreamerConfiguration<I, O> newOnSuccessProcess(BiConsumer<I, O> onSuccessProcess) {
        ON_SUCCESS_PROCESS.add(Preconditions.simpleParameterNotNull(onSuccessProcess, "onSuccessProcess"));
        return this;
    }

    public Set<BiConsumer<I, O>> getOnSuccessProcess() {
        return new HashSet<>(ON_SUCCESS_PROCESS.get());
    }

    public StreamerConfiguration<I, O> onPreProcess(BiConsumer<I, O> task) {
        ON_PRE_PROCESS.add(Preconditions.simpleParameterNotNull(task, "task"));
        return this;
    }

}
