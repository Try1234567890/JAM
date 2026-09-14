package com.github.trfiles.management.io.zippers.toZOS;

import com.github.trfiles.management.io.zippers.InstanceZipper;
import com.github.trfiles.management.io.zippers.ZipperConfiguration;

import java.util.zip.ZipOutputStream;

public abstract class ZOSZipper<I> extends InstanceZipper<I, ZipOutputStream> {
    protected ZOSZipper(I input, ZipOutputStream destination) {
        super(input, destination);
    }

    public static class ZOSZipperConfiguration<I> extends ZipperConfiguration<I, ZipOutputStream> {

    }
}
