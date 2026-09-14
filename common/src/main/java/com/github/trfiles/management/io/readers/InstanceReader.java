package com.github.trfiles.management.io.readers;

import com.github.trfiles.management.io.IOProcess;
import com.github.utilities.validators.Preconditions;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public abstract class InstanceReader<I, O> extends IOProcess<I, O> implements Reader<I, O> {
    private final I input;


    protected InstanceReader(I input) {
        this.input = Preconditions.simpleParameterNotNull(input, "input");
    }

    public I getInput() {
        return input;
    }

    protected void saveThrowable(Throwable throwable) {
        if (getConfiguration().shouldSaveExceptions())
            exceptions.add(throwable);
    }

    public abstract ReaderConfiguration<I, O> getConfiguration();

    protected abstract O readEffectively(I value, long offset, long length) throws Exception;

    @Override
    public O read(I input, long offset, long length) throws Exception {
        if (getConfiguration().SHOULD_CHECK_INDEXES.get())
            checkIndexes(input, offset, length);
        try {
            run(getConfiguration().getOnPreProcess().stream().<Runnable>map((cons) -> () -> cons.accept(input)).collect(Collectors.toSet()));
            O out = readEffectively(input, offset, length);
            run(getConfiguration().getOnSuccessProcess().stream().<Runnable>map((cons) -> () -> cons.accept(input, out)).collect(Collectors.toSet()));
            return out;
        } catch (Throwable t) {
            saveThrowable(t);
            run(getConfiguration().getOnFailProcess().stream().<Runnable>map((cons) -> () -> cons.accept(input, t)).collect(Collectors.toSet()));
            throw t;
        } finally {
            run(getConfiguration().getOnPostProcess().stream().<Runnable>map((cons) -> () -> cons.accept(input)).collect(Collectors.toSet()));
        }
    }

    private void run(Set<Runnable> runnables) {
        try {
            for (Runnable runnable : runnables) {
                runnable.run();
            }
        } catch (Throwable t) {
            saveThrowable(t);
        }
    }

    @Override
    public O read(I input, long offset) throws Exception {
        return read(input, offset, size(input));
    }

    @Override
    public O readUntil(I input, long length) throws Exception {
        return read(input, 0, length);
    }

    @Override
    public O read(I input) throws Exception {
        return read(input, 0, size(input));
    }

    public O read(long offset, long length) throws Exception {
        return read(input, offset, length);
    }

    public O read(long offset) throws Exception {
        return read(input, offset);
    }

    public O readUntil(long length) throws Exception {
        return readUntil(input, length);
    }

    public O read() throws Exception {
        return read(input);
    }

    public Optional<O> readSilently(long offset, long length) {
        return readSilently(input, offset, length);
    }

    public Optional<O> readSilently(long offset) {
        return readSilently(input, offset);
    }

    public Optional<O> readUntilSilently(long length) {
        return readUntilSilently(input, length);
    }

    public Optional<O> readSilently() {
        return readSilently(input);
    }

    public O readGet(long offset, long length, Supplier<O> def) {
        return readGet(input, offset, length, def);
    }

    public O readGet(long offset, Supplier<O> def) {
        return readGet(input, offset, def);
    }

    public O readUntilGet(long length, Supplier<O> def) {
        return readUntilGet(input, length, def);
    }

    public O readGet(Supplier<O> def) {
        return readGet(input, def);
    }

    public O readSafe(long offset, long length, O def) {
        return readSafe(input, offset, length, def);
    }

    public O readSafe(long offset, O def) {
        return readSafe(input, offset, def);
    }

    public O readUntilSafe(long length, O def) {
        return readUntilSafe(input, length, def);
    }

    public O readSafe(O def) {
        return readSafe(input, def);
    }
}
