package me.tr.trfiles.management.archives.zipper;

import java.io.IOException;

public abstract class Zipper<T, V> {

    public abstract void zipOrThrown(T zip, V value) throws IOException;

    public boolean zip(T zip, V value) {
        try {
            zipOrThrown(zip, value);
            return true;
        } catch (IOException ignore) {
        }
        return false;
    }
}
