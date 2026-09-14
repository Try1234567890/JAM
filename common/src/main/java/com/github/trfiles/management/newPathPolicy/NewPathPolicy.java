package com.github.trfiles.management.newPathPolicy;

import com.github.trfiles.management.FileCreator;
import com.github.trfiles.management.rollbackable.Rollbackable;
import com.github.trfiles.utility.ValueError;

public interface NewPathPolicy extends Rollbackable {

    default ValueError<Void> toValueError(FileCreator.Result res) {
        if (res == null) return ValueError.error(new NullPointerException("Cannot convert null reference to FileCreator.Result."));
        Throwable error = res.error();
        return new ValueError<>(null, error);
    }

}
