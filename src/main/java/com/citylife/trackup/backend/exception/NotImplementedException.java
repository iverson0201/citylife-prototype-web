package com.citylife.trackup.backend.exception;


@SuppressWarnings("serial")
public class NotImplementedException extends RuntimeException {

    public NotImplementedException() {
        super();
    }

    public NotImplementedException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public NotImplementedException(final String message) {
        super(message);
    }

    public NotImplementedException(final Throwable cause) {
        super(cause);
    }

}
