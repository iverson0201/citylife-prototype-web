package com.citylife.backend.auth;

import org.springframework.security.core.AuthenticationException;

/**
 * @author 作者 E-mail:xujw0201@gmail.com
 * 
 */
@SuppressWarnings("serial")
public class RestAuthenticationException extends AuthenticationException {

    public RestAuthenticationException(String msg) {
        super(msg);
    }

    public RestAuthenticationException(String msg, Throwable t) {
        super(msg, t);
    }
}
