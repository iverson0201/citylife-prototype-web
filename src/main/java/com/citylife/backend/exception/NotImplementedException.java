package com.citylife.backend.exception;

/**
 * @author 作者 E-mail:xujw0201@gmail.com
 * @version 创建时间：2014年9月18日 下午5:23:49
 * 
 */
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
