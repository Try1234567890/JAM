package com.github.trfiles.management.io.streamers;

import com.github.trfiles.management.io.writers.WriterBuilder;
import org.jetbrains.annotations.NotNull;

/**
 * A {@code Streamer} is the base interface for all systems that copy a {@code source} into a
 * {@code destination} by reading it in successive chunks and writing each chunk immediately,
 * instead of loading the whole {@code source} into memory beforehand (as an {@link com.github.trfiles.management.io.writers.Writer}
 * does with its {@code value}).
 * <p>
 * This is what allows a {@code Streamer} to correctly handle sources of a size that is unknown upfront,
 * or even genuinely unbounded (a growing file, a pipe, a live network stream, ...): at any given moment
 * only a single chunk is held in memory.
 *
 * @param <I> the source of the process.
 * @param <O> the destination of the process.
 */
public interface Streamer<I, O> {

    static StreamerBuilder builder() {
        return StreamerBuilder.get();
    }

    /**
     * Sentinel value for the {@code length} parameter meaning "keep streaming until the source is
     * exhausted", instead of stopping at a specific, pre-determined amount of bytes.
     */
    long UNBOUNDED = -1L;

    /**
     * Stream the sub-value of the {@code source}, peeked from the {@code offset} index (inclusive)
     * and the {@code length} index (exclusive), to the given {@code destination}, reading and writing
     * it a chunk at a time.
     * <p>
     * If the one of the indexes is out of bounds, an {@link IndexOutOfBoundsException} will be thrown by
     * default (unless the implementations specify differently). A {@code length} of {@link #UNBOUNDED}
     * disables the upper-bound check entirely: the source is streamed until it is exhausted, which is
     * what allows this method to cope with sources of a potentially infinite size.
     *
     * @param source      The source to stream
     * @param destination The destination where the source will be streamed
     * @param offset      The start index of the sub-value peeked from the {@code source} (inclusive)
     * @param length      The end index of the sub-value peeked from the {@code source} (exclusive), or {@link #UNBOUNDED}
     * @throws Exception If an error occurs while streaming to the given {@code destination}.
     */
    void stream(@NotNull I source, @NotNull O destination, long offset, long length) throws Exception;

    /**
     * Stream the sub-value of the {@code source}, peeked from the {@code offset} index (inclusive) until
     * the source is exhausted, to the given {@code destination}.
     * <p>
     * If the index is out of bounds, an {@link IndexOutOfBoundsException} will be thrown by
     * default (unless the implementations specify differently).
     *
     * @param source      The source to stream
     * @param destination The destination where the source will be streamed
     * @param offset      The start index of the sub-value peeked from the {@code source} (inclusive)
     * @throws Exception If an error occurs while streaming to the given {@code destination}.
     */
    void stream(@NotNull I source, @NotNull O destination, long offset) throws Exception;

    /**
     * Stream the sub-value of the {@code source}, peeked until the index {@code length} (exclusive)
     * is reached, to the given {@code destination}.
     * <p>
     * If the index is out of bounds, an {@link IndexOutOfBoundsException} will be thrown by
     * default (unless the implementations specify differently).
     *
     * @param source      The source to stream
     * @param destination The destination where the source will be streamed
     * @throws Exception If an error occurs while streaming to the given {@code destination}.
     */
    void streamUntil(@NotNull I source, @NotNull O destination, long length) throws Exception;

    /**
     * Stream the whole {@code source} to the given {@code destination}, until the source is exhausted.
     * <p>
     * Since no upper bound is assumed, this is the method to prefer when the {@code source} is, or may
     * be, of a potentially infinite size.
     *
     * @param source      The source to stream
     * @param destination The destination where the source will be streamed
     * @throws Exception If an error occurs while streaming to the given {@code destination}.
     */
    void stream(@NotNull I source, @NotNull O destination) throws Exception;

    /**
     * Stream the sub-value of the {@code source}, peeked from the {@code offset} index (inclusive)
     * and the {@code length} index (exclusive), to the given {@code destination}.
     * <p>
     * Any type of the {@link Exception} thrown will be silently ignored.
     *
     * @param source      The source to stream
     * @param destination The destination where the source will be streamed
     * @param offset      The start index of the sub-value peeked from the {@code source} (inclusive)
     * @param length      The end index of the sub-value peeked from the {@code source} (exclusive), or {@link #UNBOUNDED}
     */
    default void streamSilently(@NotNull I source, @NotNull O destination, long offset, long length) {
        try {
            stream(source, destination, offset, length);
        } catch (Exception _) {
        }
    }

    /**
     * Stream the sub-value of the {@code source}, peeked from the {@code offset} index (inclusive),
     * to the given {@code destination}.
     * <p>
     * Any type of the {@link Exception} thrown will be silently ignored.
     *
     * @param source      The source to stream
     * @param destination The destination where the source will be streamed
     * @param offset      The start index of the sub-value peeked from the {@code source} (inclusive)
     */
    default void streamSilently(@NotNull I source, @NotNull O destination, long offset) {
        try {
            stream(source, destination, offset);
        } catch (Exception _) {
        }
    }

    /**
     * Stream the sub-value of the {@code source}, peeked until the index {@code length} (exclusive)
     * is reached, to the given {@code destination}.
     * <p>
     * Any type of the {@link Exception} thrown will be silently ignored.
     *
     * @param source      The source to stream
     * @param destination The destination where the source will be streamed
     */
    default void streamUntilSilently(@NotNull I source, @NotNull O destination, long length) {
        try {
            streamUntil(source, destination, length);
        } catch (Exception _) {
        }
    }

    /**
     * Stream the whole {@code source} to the given {@code destination}, until the source is exhausted.
     * <p>
     * Any type of the {@link Exception} thrown will be silently ignored.
     *
     * @param source      The source to stream
     * @param destination The destination where the source will be streamed
     */
    default void streamSilently(@NotNull I source, @NotNull O destination) {
        try {
            stream(source, destination);
        } catch (Exception _) {
        }
    }
}
