package com.github.trfiles.management.io.unzippers;

import com.github.trfiles.management.io.IOProcess;
import com.github.utilities.validators.Preconditions;

import java.util.function.Predicate;
import java.util.zip.ZipEntry;

/**
 * Template-method base class shared by every concrete unzipper, mirroring
 * {@code InstanceZipper}. Fixes an {@code input}/{@code destination} pair at
 * construction time so that the no-argument {@link #unzip()}/
 * {@link #unzip(Predicate)} convenience methods can be used, in addition to
 * the explicit-argument ones inherited from {@link Unzipper}.
 *
 * @param <I> the type of the ZIP source.
 * @param <D> the type of the destination.
 */
public abstract class InstanceUnzipper<I, D> extends IOProcess<I, D> implements Unzipper<I, D> {
    private final I input;
    private final D destination;

    protected InstanceUnzipper(I input, D destination) {
        this.input = Preconditions.simpleParameterNotNull(input, "input");
        this.destination = Preconditions.simpleParameterNotNull(destination, "destination");
    }

    public I getInput() {
        return input;
    }

    public D getDestination() {
        return destination;
    }

    /**
     * The size of a ZIP source is not meaningful for an unzip operation
     * (the *uncompressed* size is only known entry by entry, while reading),
     * so, consistently with {@code InstanceZipper}, this always returns
     * {@code -1}.
     */
    @Override
    protected long _size(I value) throws Exception {
        return -1;
    }

    public abstract UnzipperConfiguration<I, D> getConfiguration();

    /**
     * Hook implemented by concrete unzippers: performs the actual extraction.
     * Implementations are responsible for guarding against Zip Slip (entries
     * escaping {@code destination}) and, where applicable, decompression-bomb
     * style abuse (entry count / uncompressed size limits).
     */
    protected abstract void unzipEffective(I input, D destination, Predicate<ZipEntry> include) throws Exception;

    @Override
    public void unzip(I input, D destination, Predicate<ZipEntry> include) throws Exception {
        unzipEffective(input, destination, include);
    }

    @Override
    public void unzip(I input, D destination) throws Exception {
        unzip(input, destination, _ -> true);
    }

    public void unzip(Predicate<ZipEntry> include) throws Exception {
        unzip(input, destination, include);
    }

    public void unzip() throws Exception {
        unzip(input, destination, _ -> true);
    }

}
