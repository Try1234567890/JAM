package com.github.trfiles.management.io.downloaders;

import com.github.trfiles.management.io.IOProcess;
import com.github.utilities.validators.Preconditions;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

/**
 * Template-method base class shared by every concrete {@link Downloader}, fixing a {@code source}/
 * {@code destination} pair at construction time (so that the no-argument {@link #download()} family of
 * convenience methods below can be used, in addition to the explicit-argument ones inherited from
 * {@link Downloader}) and wiring the {@code onPreProcess}/{@code onSuccessProcess}/{@code onFailProcess}/
 * {@code onPostProcess} hooks exposed by {@link DownloaderConfiguration}.
 * <p>
 * <b>The size of a remote {@code source} is never trusted upfront.</b> Unlike {@code InstanceReader}/
 * {@code InstanceStreamer}, {@link #_size} always returns {@code -1} here — consistent with
 * {@code InstanceZipper}/{@code InstanceUnzipper}, which do the same for an equivalent reason: a
 * server-reported size (a {@code Content-Length} header) is attacker/server-controlled and
 * cannot be relied upon to validate {@code offset}/{@code length} before a single byte has actually been
 * read (see {@code ZIStoPathUnzipper}, which counts bytes as they come off the stream instead of trusting
 * {@code ZipEntry.getSize()}). The real enforcement of how much is downloaded happens inside
 * {@code downloadEffectively(...)}, against the byte count actually read from the response, using the
 * {@code maxDownloadSize} exposed by the concrete {@code fromURI}-level configuration.
 * <p>
 * ⚠️ Unlike {@code InstanceWriter#write}/{@code InstanceStreamer#stream}, {@link #download} <b>does</b>
 * rethrow the exception it catches (after saving it and running {@code onFailProcess}), the same way
 * {@code InstanceReader#read} does — see {@code 04-conventions-and-gotchas.md} and the class docs on
 * {@link Downloader} for why a download must not be allowed to "silently" not happen.
 *
 * @param <I> the source of the process.
 * @param <D> the destination of the process.
 */
public abstract class InstanceDownloader<I, D> extends IOProcess<I, D> implements Downloader<I, D> {
    private final I source;
    private final D destination;

    protected InstanceDownloader(I source, D destination) {
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

    public abstract DownloaderConfiguration<I, D> getConfiguration();

    /**
     * Effectively performs the request and the chunk-by-chunk read/write loop between {@code source} and
     * {@code destination}. Implementations MUST NOT hold the whole response body in memory at once (same
     * constant-memory-footprint contract as {@code Streamer#streamEffectively}), and MUST NOT trust any
     * server-reported size hint (e.g. {@code Content-Length}) as a substitute for counting the bytes
     * actually read against the configured maximum download size.
     */
    protected abstract void downloadEffectively(I source, D destination, long offset, long length) throws Exception;

    /**
     * The size of a remote {@code source} is never known upfront without performing a request of its own
     * (and, even then, a server-reported size is not to be trusted) — see the class docs. Always {@code -1}.
     */
    @Override
    protected long _size(I value) throws Exception {
        return -1;
    }

    /**
     * {@inheritDoc}
     * <p>
     * Mirrors {@code InstanceStreamer#checkIndexes}: a {@code length} of {@link Downloader#UNBOUNDED} skips
     * the upper-bound check entirely, and — since {@link #_size} always returns {@code -1} here — the
     * upper-bound check against the source size is never meaningfully performed at all; only {@code offset}
     * and the relative ordering of {@code offset}/{@code length} are validated before a request is made.
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
    }

    @Override
    public void download(@NotNull I source, @NotNull D destination, long offset, long length) throws Exception {
        if (getConfiguration().SHOULD_CHECK_INDEXES.get())
            checkIndexes(source, offset, length);
        try {
            run(source, destination, getConfiguration().getOnPreProcess());
            downloadEffectively(source, destination, offset, length);
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
    public void download(@NotNull I source, @NotNull D destination, long offset) throws Exception {
        download(source, destination, offset, UNBOUNDED);
    }

    @Override
    public void downloadUntil(@NotNull I source, @NotNull D destination, long length) throws Exception {
        download(source, destination, 0, length);
    }

    @Override
    public void download(@NotNull I source, @NotNull D destination) throws Exception {
        download(source, destination, 0, UNBOUNDED);
    }

    public void download(long offset, long length) throws Exception {
        download(source, destination, offset, length);
    }

    public void download(long offset) throws Exception {
        download(source, destination, offset, UNBOUNDED);
    }

    public void downloadUntil(long length) throws Exception {
        download(source, destination, 0, length);
    }

    public void download() throws Exception {
        download(source, destination, 0, UNBOUNDED);
    }

    public void downloadSilently(long offset, long length) {
        downloadSilently(source, destination, offset, length);
    }

    public void downloadSilently(long offset) {
        downloadSilently(source, destination, offset);
    }

    public void downloadUntilSilently(long length) {
        downloadUntilSilently(source, destination, length);
    }

    public void downloadSilently() {
        downloadSilently(source, destination);
    }
}
