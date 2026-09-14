package com.github.trfiles.management.io.zippers;

import com.github.trfiles.management.io.IOProcess;
import com.github.utilities.validators.Preconditions;

import java.nio.file.Path;
import java.util.function.Predicate;

public abstract class InstanceZipper<I, D> extends IOProcess<I, D> implements Zipper<I, D> {
    private final I input;
    private final D destination;

    protected InstanceZipper(I input, D destination) {
        this.input = Preconditions.simpleParameterNotNull(input, "input");
        this.destination = Preconditions.simpleParameterNotNull(destination, "destination");
    }

    public I getInput() {
        return input;
    }

    public D getDestination() {
        return destination;
    }

    @Override
    protected long _size(I value) throws Exception {
        return -1;
    }

    public abstract ZipperConfiguration<I, D> getConfiguration();

    protected abstract void zipEffective(I input, D destination, Predicate<Path> include) throws Exception;

    @Override
    public void zip(I input, D destination, Predicate<Path> include) throws Exception {
        zipEffective(input, destination, include);
    }

    @Override
    public void zip(I input, D destination) throws Exception {
        zip(input, destination, _ -> true);
    }

    public void zip(Predicate<Path> include) throws Exception {
        zip(input, destination, include);
    }

    public void zip() throws Exception {
        zip(input, destination, _ -> true);
    }

}
