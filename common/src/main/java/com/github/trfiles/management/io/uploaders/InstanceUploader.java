package com.github.trfiles.management.io.uploaders;

import com.github.trfiles.management.io.IOProcess;
import com.github.utilities.validators.Preconditions;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

/**
 * Template-method base class shared by every concrete {@link Uploader}, fixing a {@code source}/
 * {@code destination} pair at construction time (so that the no-argument {@link #upload()} family of
 * convenience methods below can be used, in addition to the explicit-argument ones inherited from
 * {@link Uploader}) and wiring the {@code onPreProcess}/{@code onSuccessProcess}/{@code onFailProcess}/
 * {@code onPostProcess} hooks exposed by {@link UploaderConfiguration}. Mirrors
 * {@code InstanceDownloader}, with source and destination roles swapped.
 * <p>
 * <b>Unlike {@code InstanceDownloader}, the size of the source is NOT uniformly untrusted here.</b>
 * {@code InstanceDownloader#_size} always returns {@code -1} because its source is always a remote,
 * server-controlled {@link java.net.URI} in every implementation of that package. Here the source type
 * varies between concrete implementations: a local {@link java.nio.file.Path}'s size IS known upfront and
 * trustworthy (it is the caller's own filesystem), while an already-opened {@link java.io.InputStream}'s
 * size generally is NOT knowable without consuming it. {@link #_size} is therefore left abstract on
 * purpose, to be implemented per concrete source type — see {@code PathUploader#_size} (delegates to
 * {@code Files.size}) and {@code StreamUploader#_size} (always {@code -1}, for the same "can't know without
 * consuming it" reason, not because the stream is adversarial).
 * <p>
 * ⚠️ Like {@code InstanceDownloader#download} and unlike {@code InstanceWriter#write}/
 * {@code InstanceStreamer#stream}, {@link #upload} <b>does</b> rethrow the exception it catches (after
 * saving it and running {@code onFailProcess}) — see {@code 04-conventions-and-gotchas.md} and the class
 * docs on {@link Uploader} for why an upload must not be allowed to "silently" not happen.
 *
 * @param <I> the source of the process.
 * @param <D> the destination of the process.
 */
public abstract class InstanceUploader<I, D> extends IOProcess<I, D> implements Uploader<I, D> {
    private final I source;
    private final D destination;

    protected InstanceUploader(I source, D destination) {
        this.source = Preconditions.simpleParameterNotNull(source, "source");
        this.destination = Preconditions.simpleParameterNotNull(destination, "destination");
    }

    public @NotNull I getSource() {
        return source;
    }

    public @NotNull D getDestination() {
        return destination;
    }

    protected void saveThrowable(Throwable throwable) {
        if (getConfiguration().shouldSaveExceptions())
            exceptions.add(throwable);
    }

    public abstract UploaderConfiguration<I, D> getConfiguration();

    /**
     * Effectively performs the connection and the chunk-by-chunk read/write loop between {@code source}
     * and {@code destination}. Implementations MUST NOT hold the whole source content in memory at once
     * (same constant-memory-footprint contract as {@code Streamer#streamEffectively}/
     * {@code Downloader#downloadEffectively}).
     */
    protected abstract void uploadEffectively(I source, D destination, long offset, long length) throws Exception;

    /**
     * {@inheritDoc}
     * <p>
     * Deliberately left abstract here — see the class docs above for why, unlike
     * {@code InstanceDownloader}, no single "always distrust it" value is correct for every concrete
     * source type in this package.
     */
    @Override
    protected abstract long _size(I value) throws Exception;

    /**
     * {@inheritDoc}
     * <p>
     * Mirrors {@code InstanceDownloader#checkIndexes}/{@code InstanceStreamer#checkIndexes}: a
     * {@code length} of {@link Uploader#UNBOUNDED} skips the upper-bound check entirely. Additionally,
     * since {@link #_size} may legitimately return a negative value here (an unknown-size source, not
     * necessarily a distrusted one — see the class docs), the upper-bound check against the source size is
     * skipped whenever the size could not be determined, instead of being skipped unconditionally like
     * {@code InstanceDownloader} does.
     */
    @Override
    protected void checkIndexes(I value, long offset, long length) throws IOException {
        if (offset < 0) {
            throw new IndexOutOfBoundsException("The \"offset\" index is less than 0!");
        }
        if (length == UNBOUNDED) {
            return;
        }
        long size = size(value);
        if (size >= 0 && length > size) {
            throw new IndexOutOfBoundsException("The \"length\" index is greater than the size of the value!");
        }
        if (offset > length) {
            throw new IndexOutOfBoundsException("The \"offset\" index is greater than the \"length\" index!");
        }
    }

    @Override
    public void upload(@NotNull I source, @NotNull D destination, long offset, long length) throws Exception {
        if (getConfiguration().SHOULD_CHECK_INDEXES.get())
            checkIndexes(source, offset, length);
        try {
            run(source, destination, getConfiguration().getOnPreProcess());
            uploadEffectively(source, destination, offset, length);
            run(source, destination, getConfiguration().getOnSuccessProcess());
        } catch (Throwable t) {
            saveThrowable(t);
            run(getConfiguration().getOnFailProcess()
                    .stream()
                    .<Runnable>map((cons) -> () -> cons.accept(source, destination, t))
                    .collect(Collectors.toSet()));
            throw t;
        } finally {
            run(source, destination, getConfiguration().getOnPostProcess());
        }
    }

    private void run(I source, D dest, Set<BiConsumer<I, D>> consumers) {
        Set<Runnable> runnables = new HashSet<>();
        for (BiConsumer<I, D> consumer : consumers) {
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
    public void upload(@NotNull I source, @NotNull D destination, long offset) throws Exception {
        upload(source, destination, offset, UNBOUNDED);
    }

    @Override
    public void uploadUntil(@NotNull I source, @NotNull D destination, long length) throws Exception {
        upload(source, destination, 0, length);
    }

    @Override
    public void upload(@NotNull I source, @NotNull D destination) throws Exception {
        upload(source, destination, 0, UNBOUNDED);
    }

    public void upload(long offset, long length) throws Exception {
        upload(source, destination, offset, length);
    }

    public void upload(long offset) throws Exception {
        upload(source, destination, offset, UNBOUNDED);
    }

    public void uploadUntil(long length) throws Exception {
        upload(source, destination, 0, length);
    }

    public void upload() throws Exception {
        upload(source, destination, 0, UNBOUNDED);
    }

    public void uploadSilently(long offset, long length) {
        uploadSilently(source, destination, offset, length);
    }

    public void uploadSilently(long offset) {
        uploadSilently(source, destination, offset);
    }

    public void uploadUntilSilently(long length) {
        uploadUntilSilently(source, destination, length);
    }

    public void uploadSilently() {
        uploadSilently(source, destination);
    }
}
