package com.citylife.trackup.backend.exception;


@SuppressWarnings("serial")
public class OperateFailedException extends RuntimeException {
    public OperateFailedException() {
        super();
    }

    public OperateFailedException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public OperateFailedException(final String message) {
        super(message);
    }

    public OperateFailedException(final Throwable cause) {
        super(cause);
    }
}
