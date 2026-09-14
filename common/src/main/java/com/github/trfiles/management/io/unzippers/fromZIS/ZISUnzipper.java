package com.github.trfiles.management.io.unzippers.fromZIS;

import com.github.trfiles.management.io.unzippers.InstanceUnzipper;
import com.github.trfiles.management.io.unzippers.UnzipperConfiguration;

import java.util.zip.ZipInputStream;

/**
 * Base class for unzippers whose source is an already opened
 * {@link ZipInputStream}. This is where the real extraction logic lives
 * (mirrors {@code ZOSZipper}, which holds the real zipping logic on the
 * writing side).
 *
 * @param <D> the type of the destination.
 */
public abstract class ZISUnzipper<D> extends InstanceUnzipper<ZipInputStream, D> {
    protected ZISUnzipper(ZipInputStream input, D destination) {
        super(input, destination);
    }

    public static class ZISUnzipperConfiguration<D> extends UnzipperConfiguration<ZipInputStream, D> {

    }
}
