package com.citylife.trackup.backend.exception;


@SuppressWarnings("serial")
public class ExistException extends RuntimeException {

    public ExistException() {
        super();
    }

    public ExistException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public ExistException(final String message) {
        super(message);
    }

    public ExistException(final Throwable cause) {
        super(cause);
    }
}
