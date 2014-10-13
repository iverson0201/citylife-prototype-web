package com.citylife.backend.exception;

/**
 * @author 作者 E-mail:xujw0201@gmail.com
 * @version 创建时间：2014年9月18日 下午5:23:49
 * 
 */
@SuppressWarnings("serial")
public class RestException extends RuntimeException {

    public RestException() {
        super();
    }

    public RestException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public RestException(final String message) {
        super(message);
    }

    public RestException(final Throwable cause) {
        super(cause);
    }
}
