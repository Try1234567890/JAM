package com.github.trfiles.management.io.unzippers;

import java.util.function.Predicate;
import java.util.zip.ZipEntry;

/**
 * Base contract for every unzip operation: extract the entries of a ZIP
 * source {@code I} into a destination {@code D}, optionally filtering which
 * entries are extracted via a {@link Predicate}.
 *
 * @param <I> the type of the ZIP source (e.g. a {@link java.nio.file.Path}
 *            pointing to a ZIP file, or an already opened
 *            {@link java.util.zip.ZipInputStream}).
 * @param <D> the type of the destination (e.g. a {@link java.nio.file.Path}
 *            pointing to the directory entries are extracted into).
 */
public interface Unzipper<I, D> {

    /**
     * Extracts every entry of {@code input} matching {@code include} into
     * {@code destination}.
     *
     * @param input       the ZIP source to read entries from.
     * @param destination the destination entries are extracted into.
     * @param include     a predicate deciding, for each {@link ZipEntry}, whether
     *                    it should be extracted.
     * @throws Exception if the extraction fails, including when an entry
     *                    would resolve outside of {@code destination} (Zip Slip).
     */
    void unzip(I input, D destination, Predicate<ZipEntry> include) throws Exception;

    /**
     * Equivalent to {@link #unzip(Object, Object, Predicate)} with
     * {@code include = entry -> true} (every entry is extracted).
     *
     * @param input       the ZIP source to read entries from.
     * @param destination the destination entries are extracted into.
     * @throws Exception if the extraction fails.
     */
    void unzip(I input, D destination) throws Exception;

}
