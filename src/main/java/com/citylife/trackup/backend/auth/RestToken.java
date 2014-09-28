package com.citylife.trackup.backend.auth;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * @author 作者 E-mail:xujw0201@gmail.com
 * @version 创建时间：2014年9月18日 下午5:23:49
 * 
 */
@SuppressWarnings("serial")
public class RestToken extends UsernamePasswordAuthenticationToken {

    public RestToken(String key, String credentials) {
        super(key, credentials);
    }

    public RestToken(String key, String credentials, Collection<? extends GrantedAuthority> authorities) {
        super(key, credentials, authorities);
    }

    public String getKey() {
        return (String) super.getPrincipal();
    }

    public String getCredentials() {
        return (String) super.getCredentials();
    }
}
