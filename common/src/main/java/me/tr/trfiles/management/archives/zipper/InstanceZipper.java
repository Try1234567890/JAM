package me.tr.trfiles.management.archives.zipper;

import com.github.utilities.validators.Preconditions;

import java.io.IOException;

public class InstanceZipper<T, V> {
    private final T zip;
    private final V value;
    private final Zipper<T, V> zipper;

    public InstanceZipper(T zip, V value, Zipper<T, V> zipper) {
        this.zip = Preconditions.parameterNotNull(zip, "zip");
        this.value = Preconditions.parameterNotNull(value, "value");
        this.zipper = Preconditions.parameterNotNull(zipper, "zipper");
    }

    public T getZip() {
        return zip;
    }

    public V getValue() {
        return value;
    }

    public Zipper<T, V> getZipper() {
        return zipper;
    }

    public void zipOrThrown() throws IOException {
        zipper.zipOrThrown(zip, value);
    }

    public boolean zip() {
        return zipper.zip(zip, value);
    }
}
