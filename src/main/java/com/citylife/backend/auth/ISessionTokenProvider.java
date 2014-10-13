package com.citylife.backend.auth;

import com.google.common.collect.ImmutableMap;

import java.util.Enumeration;

/**
 * @author 作者 E-mail:xujw0201@gmail.com
 * 
 */
public interface ISessionTokenProvider {
		public ImmutableMap<SessionKeys, ? extends Object> setSession(String AppCode, String username, String Password);
		public ImmutableMap<SessionKeys, ? extends Object> getSession(String token);
		public void removeSession(String token);
		public void setTimeout(long timeoutInMilliseconds);
		public Enumeration<String> getTokens();
}
