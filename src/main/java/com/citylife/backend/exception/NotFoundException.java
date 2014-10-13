package com.citylife.backend.exception;

/**
 * @author 作者 E-mail:xujw0201@gmail.com
 * @version 创建时间：2014年9月18日 下午5:23:49
 * 
 */
@SuppressWarnings("serial")
public class NotFoundException extends RuntimeException {

    public NotFoundException() {
        super();
    }

    public NotFoundException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public NotFoundException(final String message) {
        super(message);
    }

    public NotFoundException(final Throwable cause) {
        super(cause);
    }

}
