package com.github.trfiles.management.io.writers;

import org.jetbrains.annotations.NotNull;

public interface Writer<V, O> {


     static WriterBuilder builder() {
        return WriterBuilder.get();
    }

    /**
     * Write the sub-value of the {@code value}, peeked from the {@code offset} index (inclusive)
     * and the {@code length} index (exclusive), to the given {@code destination}.
     * <p>
     * If the one of the indexes is out of bounds, an {@link IndexOutOfBoundsException} will be thrown by
     * default (unless the implementations specify differently).
     *
     * @param value       The value to write
     * @param destination The destination where the value will be written
     * @param offset      The start index of the sub-value peeked from the {@code value} (inclusive)
     * @param length      The end index of the sub-value peeked from the {@code value} (exclusive)
     * @throws Exception If an error occurs while writing to the given {@code destination}.
     */
    void write(@NotNull V value, @NotNull O destination, long offset, long length) throws Exception;

    /**
     * Write the sub-value of the {@code value}, peeked from the {@code offset} index (inclusive),
     * to the given {@code destination}.
     * <p>
     * If the index is out of bounds, an {@link IndexOutOfBoundsException} will be thrown by
     * default (unless the implementations specify differently).
     *
     * @param value       The value to write
     * @param destination The destination where the value will be written
     * @param offset      The start index of the sub-value peeked from the {@code value} (inclusive)
     * @throws Exception If an error occurs while writing to the given {@code destination}.
     */
    void write(@NotNull V value, @NotNull O destination, long offset) throws Exception;

    /**
     * Write the sub-value of the {@code value}, peeked until the index {@code length} (exclusive)
     * is reached, to the given {@code destination}.
     * <p>
     * If the index is out of bounds, an {@link IndexOutOfBoundsException} will be thrown by
     * default (unless the implementations specify differently).
     *
     * @param value       The value to write
     * @param destination The destination where the value will be written
     * @throws Exception If an error occurs while writing to the given {@code destination}.
     */
    void writeUntil(@NotNull V value, @NotNull O destination, long length) throws Exception;

    /**
     * Write the value of the {@code value} to the given {@code destination}.
     *
     * @param value       The value to write
     * @param destination The destination where the value will be written
     * @throws Exception If an error occurs while writing to the given {@code destination}.
     */
    void write(@NotNull V value, @NotNull O destination) throws Exception;

    /**
     * Write the sub-value of the {@code value} peeked from the {@code offset} index (inclusive)
     * and the {@code length} index (exclusive) to the given {@code destination}.
     * <p>
     * Any type of the {@link Exception} thrown will be silently ignored.
     *
     * @param value       The value to write
     * @param destination The destination where the value will be written
     * @param offset      The start index of the sub-value peeked from the {@code value} (inclusive)
     * @param length      The end index of the sub-value peeked from the {@code value} (exclusive)
     */
    default void writeSilently(@NotNull V value, @NotNull O destination, long offset, long length) {
        try {
            write(value, destination, offset, length);
        } catch (Exception _) {
        }
    }

    /**
     * Write the sub-value of the {@code value}, peeked from the {@code offset} index (inclusive),
     * to the given {@code destination}.
     * <p>
     * Any type of the {@link Exception} thrown will be silently ignored.
     *
     * @param value       The value to write
     * @param destination The destination where the value will be written
     * @param offset      The start index of the sub-value peeked from the {@code value} (inclusive)
     */
    default void writeSilently(@NotNull V value, @NotNull O destination, long offset) {
        try {
            write(value, destination, offset);
        } catch (Exception _) {
        }
    }

    /**
     * Write the sub-value of the {@code value}, peeked until the index {@code length} (exclusive)
     * is reached, to the given {@code destination}.
     * <p>
     * Any type of the {@link Exception} thrown will be silently ignored.
     *
     * @param value       The value to write
     * @param destination The destination where the value will be written
     */
    default void writeUntilSilently(@NotNull V value, @NotNull O destination, long length) {
        try {
            writeUntil(value, destination, length);
        } catch (Exception _) {
        }
    }

    /**
     * Write the value of the {@code value} to the given {@code destination}.
     * <p>
     * Any type of the {@link Exception} thrown will be silently ignored.
     *
     * @param value       The value to write
     * @param destination The destination where the value will be written
     */
    default void writeSilently(@NotNull V value, @NotNull O destination) {
        try {
            write(value, destination);
        } catch (Exception _) {
        }
    }
}
