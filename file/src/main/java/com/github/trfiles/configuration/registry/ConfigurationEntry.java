package com.github.trfiles.configuration.registry;

import com.github.trfiles.configuration.FileConfiguration;
import com.github.trfiles.management.FileExtension;

import java.nio.file.Path;
import java.util.Set;

/**
 * A {@code Configuration Entry} is the object that every {@link FileConfiguration} must have.
 * <p>
 * This represents the {@link FileConfiguration} inside the {@link ConfigurationRegistry}.
 */
public interface ConfigurationEntry {

    /**
     * Create a new instance the {@link FileConfiguration} that this entry represents.
     *
     * @param path The path to create the instance for.
     * @return The new instance.
     */
    FileConfiguration newInstance(Path path);

    /**
     * The extension of the file that the file of {@link FileConfiguration} can have.
     *
     * @return The {@link Set} of {@link FileExtension} that the file of {@link FileConfiguration} can have.
     */
    Set<FileExtension> extensions();

    /**
     * Retrieve the empty representation of the file.
     *
     * @return the empty representation of the file
     */
    String getEmpty();

    /**
     * This is an optional predicate that can be used to implements a custom
     * logic to check if the file is valid for the {@link FileConfiguration} that this entry represents.
     * <p>
     * If this predicate returns true or the file extension is inside {@link #extensions()},
     * the {@link FileConfiguration} will be loaded.
     *
     * @return {@code true} if the file is for the {@link FileConfiguration} that this entry represents, {@code false} otherwise.
     */
    default boolean isValid(Path path) {
        return true;
    }
}
