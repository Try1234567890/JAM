package com.github.trfiles.management.io.uploaders;

import org.jetbrains.annotations.NotNull;

/**
 * An {@code Uploader} is the base interface for all systems that copy a local {@code source} into a
 * remote {@code destination} (identified, in the concrete implementations of this package, by a
 * {@link java.net.URI}), reading it in successive chunks and writing each chunk immediately over the
 * network — the mirror image of {@link com.github.trfiles.management.io.downloaders.Downloader}, and the
 * same "constant memory footprint" idea as {@link com.github.trfiles.management.io.streamers.Streamer},
 * applied to a destination that is remote and only reachable over the network instead of local.
 * <p>
 * <b>Like {@link com.github.trfiles.management.io.downloaders.Downloader#download}, and UNLIKE
 * {@link com.github.trfiles.management.io.streamers.Streamer#stream}/
 * {@link com.github.trfiles.management.io.writers.Writer#write}, {@link #upload} DOES rethrow</b> the
 * exception raised while uploading (see {@code InstanceUploader} and
 * {@code 04-conventions-and-gotchas.md}): an upload that silently "succeeds" empty/truncated, or whose
 * scheme rejection or size-limit got swallowed, would leave a caller believing data was safely persisted
 * remotely when it was not — the same correctness/security foot-gun a download has, just on the other end
 * of the wire.
 *
 * @param <I> the source of the process (a local resource — a {@link java.nio.file.Path} or an already
 *            opened {@link java.io.InputStream} — in every implementation currently in this package).
 * @param <D> the destination of the process (a {@link java.net.URI} in every implementation currently in
 *            this package).
 */
public interface Uploader<I, D> {

    static UploaderBuilder builder() {
        return UploaderBuilder.get();
    }

    /**
     * Sentinel value for the {@code length} parameter meaning "upload until the source is exhausted",
     * instead of stopping at a specific, pre-determined amount of bytes.
     */
    long UNBOUNDED = -1L;

    /**
     * Uploads the sub-value of {@code source}, peeked from the {@code offset} index (inclusive) and the
     * {@code length} index (exclusive), into {@code destination}, reading and writing it a chunk at a
     * time. A {@code length} of {@link #UNBOUNDED} uploads the source until it is exhausted.
     *
     * @param source      The local source to upload.
     * @param destination The remote destination the source is uploaded into.
     * @param offset      The start index of the sub-value peeked from {@code source} (inclusive).
     * @param length      The end index of the sub-value peeked from {@code source} (exclusive), or
     *                    {@link #UNBOUNDED}.
     * @throws Exception If an error occurs while uploading, including a rejected scheme, a response status
     *                    the caller must handle, or the configured size limit being exceeded.
     */
    void upload(@NotNull I source, @NotNull D destination, long offset, long length) throws Exception;

    /**
     * Uploads the sub-value of {@code source}, peeked from the {@code offset} index (inclusive) until the
     * source is exhausted, into {@code destination}.
     */
    void upload(@NotNull I source, @NotNull D destination, long offset) throws Exception;

    /**
     * Uploads the sub-value of {@code source}, peeked until the index {@code length} (exclusive) is
     * reached, into {@code destination}.
     */
    void uploadUntil(@NotNull I source, @NotNull D destination, long length) throws Exception;

    /**
     * Uploads the whole {@code source} into {@code destination}, until the source is exhausted.
     */
    void upload(@NotNull I source, @NotNull D destination) throws Exception;

    /**
     * Same as {@link #upload(Object, Object, long, long)}, but any {@link Exception} thrown is silently
     * ignored.
     */
    default void uploadSilently(@NotNull I source, @NotNull D destination, long offset, long length) {
        try {
            upload(source, destination, offset, length);
        } catch (Exception _) {
        }
    }

    /**
     * Same as {@link #upload(Object, Object, long)}, but any {@link Exception} thrown is silently ignored.
     */
    default void uploadSilently(@NotNull I source, @NotNull D destination, long offset) {
        try {
            upload(source, destination, offset);
        } catch (Exception _) {
        }
    }

    /**
     * Same as {@link #uploadUntil(Object, Object, long)}, but any {@link Exception} thrown is silently
     * ignored.
     */
    default void uploadUntilSilently(@NotNull I source, @NotNull D destination, long length) {
        try {
            uploadUntil(source, destination, length);
        } catch (Exception _) {
        }
    }

    /**
     * Same as {@link #upload(Object, Object)}, but any {@link Exception} thrown is silently ignored.
     */
    default void uploadSilently(@NotNull I source, @NotNull D destination) {
        try {
            upload(source, destination);
        } catch (Exception _) {
        }
    }
}
