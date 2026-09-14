package com.github.trfiles.management.newPathPolicy;

import com.github.trfiles.utility.ValueError;

public class DoNothing implements NewPathPolicy {
    private DoNothing() {}

    private static final class Holder {
        private static final DoNothing INSTANCE = new DoNothing();
    }

    public static DoNothing get() {
        return Holder.INSTANCE;
    }

    @Override
    public ValueError<Void> run() {
        return ValueError.empty();
    }

    @Override
    public ValueError<Void> undo() {
        return ValueError.empty();
    }

    @Override
    public ValueError<Void> redo() {
        return ValueError.empty();
    }

    @Override
    public boolean wasUndid() {
        return false;
    }
}
