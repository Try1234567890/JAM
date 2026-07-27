package me.tr.trfiles.management.archives.unzipper;

import com.github.utilities.validators.Preconditions;

import java.io.IOException;
import java.util.Optional;

public record InstanceUnZipper<T, V>(T zip, V to, UnZipper<T, V> unzipper) {

    public InstanceUnZipper(T zip, V to, UnZipper<T, V> unzipper) {
        this.zip = Preconditions.parameterNotNull(zip, "zip");
        this.to = Preconditions.parameterNotNull(to, "to");
        this.unzipper = Preconditions.parameterNotNull(unzipper, "unzipper");
    }

    public V unzipOrThrown(T zip, V to) throws IOException {
        return unzipper.unzipOrThrown(zip, to);
    }

    public Optional<V> unzip(T zip, V to) {
        return unzipper.unzip(zip, to);
    }

    public V unzipOrNull(T zip, V to) {
        return unzipper.unzipOrNull(zip, to);
    }
}
