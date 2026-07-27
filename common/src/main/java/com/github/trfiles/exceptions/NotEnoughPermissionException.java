package com.github.trfiles.exceptions;

public class NotEnoughPermissionException extends RuntimeException {

    public NotEnoughPermissionException() {
    }

    public NotEnoughPermissionException(String message) {
        super(message);
    }

    public NotEnoughPermissionException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotEnoughPermissionException(Throwable cause) {
        super(cause);
    }

    public NotEnoughPermissionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
