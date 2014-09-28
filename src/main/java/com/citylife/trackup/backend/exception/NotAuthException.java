package com.citylife.trackup.backend.exception;


@SuppressWarnings("serial")
public class NotAuthException extends RuntimeException {

    public NotAuthException() {
        super();
    }

    public NotAuthException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public NotAuthException(final String message) {
        super(message);
    }

    public NotAuthException(final Throwable cause) {
        super(cause);
    }

}
