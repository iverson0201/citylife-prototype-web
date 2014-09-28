package com.citylife.trackup.backend.exception;

/**
 * @author 作者 E-mail:xujw0201@gmail.com
 * @version 创建时间：2014年9月18日 下午5:23:49
 * 
 */
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
