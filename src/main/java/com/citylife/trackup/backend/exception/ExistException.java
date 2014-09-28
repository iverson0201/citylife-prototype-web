package com.citylife.trackup.backend.exception;

/**
 * @author 作者 E-mail:xujw0201@gmail.com
 * @version 创建时间：2014年9月18日 下午5:23:49
 * 
 */
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
