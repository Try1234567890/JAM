package com.github.trfiles.management.io.unzippers;

/**
 * Base, empty configuration for {@link Unzipper} implementations. Concrete
 * unzippers nest their own {@code Configuration} class extending this one,
 * following the same "matrioska" pattern used by readers/writers/streamers/
 * zippers (see {@code 01-architecture.md}).
 *
 * @param <I> the type of the ZIP source.
 * @param <D> the type of the destination.
 */
public abstract class UnzipperConfiguration<I, D> {

}
