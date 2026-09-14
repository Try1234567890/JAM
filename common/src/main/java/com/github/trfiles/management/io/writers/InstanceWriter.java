package com.github.trfiles.management.io.writers;

import com.github.trfiles.management.io.IOProcess;
import com.github.utilities.validators.Preconditions;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

public abstract class InstanceWriter<V, O> extends IOProcess<V, O> implements Writer<V, O> {
    private final V value;
    private final O destination;

    protected InstanceWriter(V value, O destination) {
        this.value = Preconditions.simpleParameterNotNull(value, "value");
        this.destination = Preconditions.simpleParameterNotNull(destination, "destination");
    }

    public @NotNull V getValue() {
        return value;
    }

    public @NotNull O getDestination() {
        return destination;
    }

    protected void saveThrowable(Throwable throwable) {
        if (getConfiguration().shouldSaveExceptions())
            exceptions.add(throwable);
    }

    public abstract WriterConfiguration<V, O> getConfiguration();

    protected abstract void writeEffectively(V value, O destination, long offset, long length) throws Exception;

    @Override
    public void write(@NotNull V value, @NotNull O destination, long offset, long length) throws Exception {
        if (getConfiguration().SHOULD_CHECK_INDEXES.get())
            checkIndexes(value, offset, length);
        try {
            run(value, destination, getConfiguration().getOnPreProcess());
            writeEffectively(value, destination, offset, length);
            run(value, destination, getConfiguration().getOnSuccessProcess());
        } catch (Throwable t) {
            saveThrowable(t);
            run(getConfiguration().getOnFailProcess()
                    .stream()
                    .<Runnable>map((cons) -> () -> cons.accept(value, destination, t))
                    .collect(Collectors.toSet()));
        } finally {
            run(value, destination, getConfiguration().getOnPostProcess());
        }
    }

    private void run(V value, O dest, Set<BiConsumer<V, O>> consumers) {
        Set<Runnable> runnables = new HashSet<>();
        for (BiConsumer<V, O> consumer : consumers) {
            runnables.add(() -> consumer.accept(value, dest));
        }
        run(runnables);
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
    public void write(@NotNull V value, @NotNull O destination, long offset) throws Exception {
        write(value, destination, offset, size(value));
    }

    @Override
    public void writeUntil(@NotNull V value, @NotNull O destination, long length) throws Exception {
        write(value, destination, 0, length);
    }

    @Override
    public void write(@NotNull V value, @NotNull O destination) throws Exception {
        write(value, destination, 0, size(value));
    }

    public void write(long offset, long length) throws Exception {
        write(value, destination, offset, length);
    }

    public void write(long offset) throws Exception {
        write(value, destination, offset, size(value));
    }

    public void writeUntil(long length) throws Exception {
        write(value, destination, 0, length);
    }

    public void write() throws Exception {
        write(value, destination, 0, size(value));
    }

    public void writeSilently(long offset, long length) {
        writeSilently(value, destination, offset, length);
    }

    public void writeSilently(long offset) {
        writeSilently(value, destination, offset);
    }

    public void writeUntilSilently(long length) {
        writeUntilSilently(value, destination, length);
    }

    public void writeSilently() {
        writeSilently(value, destination);
    }
}
