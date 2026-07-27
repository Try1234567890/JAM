package com.github.trfiles.exceptions;

public class UnexpectedValueType extends RuntimeException {

    public UnexpectedValueType() {
    }

    public UnexpectedValueType(String message) {
        super(message);
    }

    public UnexpectedValueType(String message, Throwable cause) {
        super(message, cause);
    }

    public UnexpectedValueType(Throwable cause) {
        super(cause);
    }

    public UnexpectedValueType(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
