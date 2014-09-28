package com.citylife.trackup.backend.auth;

import org.springframework.security.core.AuthenticationException;

@SuppressWarnings("serial")
public class RestAuthenticationException extends AuthenticationException {

    public RestAuthenticationException(String msg) {
        super(msg);
    }

    public RestAuthenticationException(String msg, Throwable t) {
        super(msg, t);
    }
}
