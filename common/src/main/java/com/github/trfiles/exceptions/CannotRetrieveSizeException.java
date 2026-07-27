package com.github.trfiles.exceptions;

public class CannotRetrieveSizeException extends RuntimeException {

    public CannotRetrieveSizeException() {
    }

    public CannotRetrieveSizeException(String message) {
        super(message);
    }

    public CannotRetrieveSizeException(String message, Throwable cause) {
        super(message, cause);
    }

    public CannotRetrieveSizeException(Throwable cause) {
        super(cause);
    }

    public CannotRetrieveSizeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
