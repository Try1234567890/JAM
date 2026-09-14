package com.github.trfiles.management.io.zippers.toPath;

import com.github.trfiles.management.FileUtility;
import com.github.trfiles.management.io.zippers.InstanceZipper;
import com.github.trfiles.management.io.zippers.ZipperConfiguration;
import com.github.trfiles.management.io.zippers.toZOS.ZOSZipper;
import com.github.trfiles.management.managers.FileManager;
import com.github.trfiles.management.newPathPolicy.forZIP.CreateZIPAndDirectories;
import com.github.trfiles.management.newPathPolicy.forZIP.NewZIPPolicy;

import java.io.IOException;
import java.nio.file.Path;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.zip.ZipOutputStream;

public abstract class PathZipper<I> extends InstanceZipper<I, Path> {
    protected PathZipper(I input, Path destination) {
        super(input, destination);
    }

    @Override
    protected void zipEffective(I input, Path destination, Predicate<Path> include) throws Exception {

        if (!FileUtility.isZip(destination)) throw new IOException("Destination zip file is not a zip file.");
        try (ZipOutputStream zos = new ZipOutputStream(FileManager.newOutputStream(destination))) {
            toZOS(input, zos).zip(include);
        }
    }

    protected abstract ZOSZipper<I> toZOS(I input, ZipOutputStream destination);

    public static class PathZipperConfiguration<I> extends ZipperConfiguration<I, Path> {
        private Function<Path, ? extends NewZIPPolicy> newZIPPolicy = CreateZIPAndDirectories::new;

        public Function<Path, ? extends NewZIPPolicy> getNewZIPPolicy() {
            return newZIPPolicy != null ? newZIPPolicy : CreateZIPAndDirectories::new;
        }

        public PathZipperConfiguration<I> withNewZIPPolicy(Function<Path, ? extends NewZIPPolicy> newZIPPolicy) {
            this.newZIPPolicy = newZIPPolicy;
            return this;
        }
    }

}
