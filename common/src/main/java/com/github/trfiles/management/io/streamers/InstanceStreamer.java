package com.github.trfiles.management.io.streamers;

import com.github.trfiles.management.io.IOProcess;
import com.github.utilities.validators.Preconditions;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

public abstract class InstanceStreamer<I, O> extends IOProcess<I, O> implements Streamer<I, O> {
    private final I source;
    private final O destination;

    protected InstanceStreamer(I source, O destination) {
        this.source = Preconditions.simpleParameterNotNull(source, "source");
        this.destination = Preconditions.simpleParameterNotNull(destination, "destination");
    }

    public @NotNull I getSource() {
        return source;
    }

    public @NotNull O getDestination() {
        return destination;
    }

    protected void saveThrowable(Throwable throwable) {
        if (getConfiguration().shouldSaveExceptions())
            exceptions.add(throwable);
    }

    public abstract StreamerConfiguration<I, O> getConfiguration();

    /**
     * Effectively performs the chunk-by-chunk read/write loop between {@code source} and {@code destination}.
     * Implementations MUST NOT ever hold the whole {@code source} in memory at once: a {@code Streamer} is
     * meant to keep a constant memory footprint regardless of how big (potentially infinite) the source is.
     */
    protected abstract void streamEffectively(I source, O destination, long offset, long length) throws Exception;

    /**
     * {@inheritDoc}
     * <p>
     * Unlike {@link IOProcess#checkIndexes(Object, long, long)}, a {@code length} of {@link Streamer#UNBOUNDED}
     * skips the upper-bound check entirely, and a {@code source} whose size cannot be determined upfront
     * (e.g. a genuinely unbounded stream) is not treated as an error: it is simply not validated.
     */
    @Override
    protected void checkIndexes(I value, long offset, long length) {
        if (offset < 0) {
            throw new IndexOutOfBoundsException("The \"offset\" index is less than 0!");
        }
        if (length == UNBOUNDED) {
            return;
        }
        if (offset > length) {
            throw new IndexOutOfBoundsException("The \"offset\" index is greater than the \"length\" index!");
        }

        long size;
        try {
            size = size(value);
        } catch (IOException e) {
            // the size of the source could not be determined (e.g. it is a genuinely unbounded stream):
            // there is nothing to validate the requested length against.
            return;
        }
        if (size >= 0 && length > size) {
            throw new IndexOutOfBoundsException("The \"length\" index is greater than the size of the source!");
        }
    }

    @Override
    public void stream(@NotNull I source, @NotNull O destination, long offset, long length) throws Exception {
        if (getConfiguration().SHOULD_CHECK_INDEXES.get())
            checkIndexes(source, offset, length);
        try {
            run(source, destination, getConfiguration().getOnPreProcess());
            streamEffectively(source, destination, offset, length);
            run(source, destination, getConfiguration().getOnSuccessProcess());
        } catch (Throwable t) {
            saveThrowable(t);
            run(getConfiguration().getOnFailProcess()
                    .stream()
                    .<Runnable>map((cons) -> () -> cons.accept(source, destination, t))
                    .collect(Collectors.toSet()));
        } finally {
            run(source, destination, getConfiguration().getOnPostProcess());
        }
    }

    private void run(I source, O dest, Set<BiConsumer<I, O>> consumers) {
        Set<Runnable> runnables = new HashSet<>();
        for (BiConsumer<I, O> consumer : consumers) {
            runnables.add(() -> consumer.accept(source, dest));
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
    public void stream(@NotNull I source, @NotNull O destination, long offset) throws Exception {
        stream(source, destination, offset, UNBOUNDED);
    }

    @Override
    public void streamUntil(@NotNull I source, @NotNull O destination, long length) throws Exception {
        stream(source, destination, 0, length);
    }

    @Override
    public void stream(@NotNull I source, @NotNull O destination) throws Exception {
        stream(source, destination, 0, UNBOUNDED);
    }

    public void stream(long offset, long length) throws Exception {
        stream(source, destination, offset, length);
    }

    public void stream(long offset) throws Exception {
        stream(source, destination, offset, UNBOUNDED);
    }

    public void streamUntil(long length) throws Exception {
        stream(source, destination, 0, length);
    }

    public void stream() throws Exception {
        stream(source, destination, 0, UNBOUNDED);
    }

    public void streamSilently(long offset, long length) {
        streamSilently(source, destination, offset, length);
    }

    public void streamSilently(long offset) {
        streamSilently(source, destination, offset);
    }

    public void streamUntilSilently(long length) {
        streamUntilSilently(source, destination, length);
    }

    public void streamSilently() {
        streamSilently(source, destination);
    }
}
