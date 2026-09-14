package com.github.trfiles.management.io.downloaders;

import com.github.trfiles.utility.TriConsumer;
import com.github.utilities.options.BooleanOption;
import com.github.utilities.options.SetOption;
import com.github.utilities.validators.Preconditions;

import java.util.HashSet;
import java.util.Set;
import java.util.function.BiConsumer;

/**
 * Base, source/destination-agnostic configuration shared by every {@code Downloader}, mirroring
 * {@link com.github.trfiles.management.io.streamers.StreamerConfiguration}: buffer size, the
 * exception-collecting behavior, and the pre/post/success/fail processing hooks.
 * <p>
 * Network-specific and security-relevant settings (allowed URI schemes, redirect handling, timeouts, the
 * maximum amount of bytes that may be downloaded, ...) are <b>not</b> here: they live in
 * {@code fromURI}'s own configuration classes, one level down, exactly like {@code PathZipperConfiguration}
 * holds the {@code NewZIPPolicy} instead of the base {@code ZipperConfiguration}.
 *
 * @param <I> the source of the process.
 * @param <D> the destination of the process.
 */
public class DownloaderConfiguration<I, D> {
    /**
     * <b>INTERNAL OPTION! THIS MUST NOT BE ACCESSIBLE FROM EXTERNAL SOURCES</b>
     */
    protected final BooleanOption SHOULD_CHECK_INDEXES = new BooleanOption(true);

    /**
     * The default amount of bytes read from the response body, and immediately written to the
     * destination, on every single iteration of the download loop.
     */
    public static final int DEFAULT_BUFFER_SIZE = 8192;

    private final BooleanOption SAVE_EXCEPTIONS = new BooleanOption(true);
    private int bufferSize = DEFAULT_BUFFER_SIZE;
    private final SetOption<BiConsumer<I, D>> ON_PRE_PROCESS = new SetOption<>(new HashSet<>());
    private final SetOption<BiConsumer<I, D>> ON_POST_PROCESS = new SetOption<>(new HashSet<>());
    private final SetOption<BiConsumer<I, D>> ON_SUCCESS_PROCESS = new SetOption<>(new HashSet<>());
    private final SetOption<TriConsumer<I, D, Throwable>> ON_FAIL_PROCESS = new SetOption<>(new HashSet<>());

    public DownloaderConfiguration<I, D> setSaveExceptions(boolean value) {
        SAVE_EXCEPTIONS.set(value);
        return this;
    }

    public DownloaderConfiguration<I, D> saveExceptions() {
        return setSaveExceptions(true);
    }

    public DownloaderConfiguration<I, D> notSaveExceptions() {
        return setSaveExceptions(false);
    }

    public boolean shouldSaveExceptions() {
        return Preconditions.simpleNotNull(SAVE_EXCEPTIONS.get(), true);
    }

    /**
     * Sets the size, in bytes, of the chunks read from the response body and immediately written to the
     * destination on every iteration of the download loop.
     *
     * @param bufferSize The size, in bytes, of a single chunk. Must be greater than 0.
     */
    public DownloaderConfiguration<I, D> setBufferSize(int bufferSize) {
        if (bufferSize <= 0) {
            throw new IllegalArgumentException("The \"bufferSize\" must be greater than 0!");
        }
        this.bufferSize = bufferSize;
        return this;
    }

    public int getBufferSize() {
        return bufferSize;
    }

    public DownloaderConfiguration<I, D> newOnPreProcess(Set<BiConsumer<I, D>> onPreProcess) {
        ON_PRE_PROCESS.addAll(Preconditions.simpleParameterNotNull(onPreProcess, "onPreProcess"));
        return this;
    }

    public DownloaderConfiguration<I, D> newOnPreProcess(BiConsumer<I, D> onPreProcess) {
        ON_PRE_PROCESS.add(Preconditions.simpleParameterNotNull(onPreProcess, "onPreProcess"));
        return this;
    }

    public Set<BiConsumer<I, D>> getOnPreProcess() {
        return new HashSet<>(ON_PRE_PROCESS.get());
    }

    public DownloaderConfiguration<I, D> newOnPostProcess(Set<BiConsumer<I, D>> onPostProcess) {
        ON_POST_PROCESS.addAll(Preconditions.simpleParameterNotNull(onPostProcess, "onPostProcess"));
        return this;
    }

    public DownloaderConfiguration<I, D> newOnPostProcess(BiConsumer<I, D> onPostProcess) {
        ON_POST_PROCESS.add(Preconditions.simpleParameterNotNull(onPostProcess, "onPostProcess"));
        return this;
    }

    public Set<BiConsumer<I, D>> getOnPostProcess() {
        return new HashSet<>(ON_POST_PROCESS.get());
    }

    public DownloaderConfiguration<I, D> newOnFailProcess(Set<TriConsumer<I, D, Throwable>> onFailProcess) {
        ON_FAIL_PROCESS.addAll(Preconditions.simpleParameterNotNull(onFailProcess, "onFailProcess"));
        return this;
    }

    public DownloaderConfiguration<I, D> newOnFailProcess(TriConsumer<I, D, Throwable> onFailProcess) {
        ON_FAIL_PROCESS.add(Preconditions.simpleParameterNotNull(onFailProcess, "onFailProcess"));
        return this;
    }

    public Set<TriConsumer<I, D, Throwable>> getOnFailProcess() {
        return new HashSet<>(ON_FAIL_PROCESS.get());
    }

    public DownloaderConfiguration<I, D> newOnSuccessProcess(Set<BiConsumer<I, D>> onSuccessProcess) {
        ON_SUCCESS_PROCESS.addAll(Preconditions.simpleParameterNotNull(onSuccessProcess, "onSuccessProcess"));
        return this;
    }

    public DownloaderConfiguration<I, D> newOnSuccessProcess(BiConsumer<I, D> onSuccessProcess) {
        ON_SUCCESS_PROCESS.add(Preconditions.simpleParameterNotNull(onSuccessProcess, "onSuccessProcess"));
        return this;
    }

    public Set<BiConsumer<I, D>> getOnSuccessProcess() {
        return new HashSet<>(ON_SUCCESS_PROCESS.get());
    }

}