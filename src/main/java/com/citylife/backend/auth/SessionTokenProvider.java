package com.citylife.backend.auth;
import com.google.common.collect.ImmutableMap;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author 作者 E-mail:xujw0201@gmail.com
 * 
 */
public class SessionTokenProvider implements ISessionTokenProvider {

    TimerTask task = new TimerTask() {
        @SuppressWarnings("unused")
		@Override
        public void run() {
            Enumeration<String> tokens=getTokens();
            long totalTokens=0;
            long removedTokens=0;
            while (tokens.hasMoreElements()){
                totalTokens++;
                if (isExpired(tokens.nextElement())) removedTokens++;
            }
        }
    };
	protected final static ConcurrentHashMap<String,ImmutableMap<SessionKeys,? extends Object>> sessions=new ConcurrentHashMap<String, ImmutableMap<SessionKeys,? extends Object>>();
	protected long expiresInMilliseconds=0; //default expiration of session tokens
	protected long  sessionClenanerLaunchInMinutes=60; //the session cleaner will be launch each x minutes.

	private Timer sessionCleaner=null;
	private static SessionTokenProvider me; 
	
	private static ISessionTokenProvider initialize(){
		if (me==null) me=new SessionTokenProvider();
		return me;
	}
	public static ISessionTokenProvider getSessionTokenProvider(){
		return initialize();
	}
	
	public static void destroySessionTokenProvider(){
		if (me!=null && me.sessionCleaner!=null) {
			me.sessionCleaner.cancel();
		}
		me=null;
	}
	
	public SessionTokenProvider(){
        sessionCleaner = new Timer();
		setTimeout(expiresInMilliseconds);
		startSessionCleaner(sessionClenanerLaunchInMinutes*60000); //converts minutes in milliseconds
	};	
	
	public void setTimeout(long timeoutInMilliseconds){
		this.expiresInMilliseconds=timeoutInMilliseconds;
	}	//setTimeout
	
	@Override
	public ImmutableMap<SessionKeys, ? extends Object> setSession(String AppCode, String username,	String password) {
		UUID token = UUID.randomUUID();
		ImmutableMap<SessionKeys, ? extends Object> info = ImmutableMap.of
				(SessionKeys.APP_CODE, AppCode,
                        SessionKeys.TOKEN, token.toString(),
                        SessionKeys.USERNAME, username,
                        SessionKeys.PASSWORD, password,
                        SessionKeys.EXPIRE_TIME, (new Date()).getTime() + expiresInMilliseconds);
		sessions.put(token.toString(), info);
		return info;
	}

	@Override
	public ImmutableMap<SessionKeys, ? extends Object> getSession(String token) {
		if (isExpired(token)){
			return null;
		}
		ImmutableMap<SessionKeys, ? extends Object> info = sessions.get(token);
		ImmutableMap<SessionKeys, ? extends Object> newInfo	= ImmutableMap.of
				(SessionKeys.APP_CODE, info.get(SessionKeys.APP_CODE),
                        SessionKeys.TOKEN, token,
                        SessionKeys.USERNAME, info.get(SessionKeys.USERNAME),
                        SessionKeys.PASSWORD, info.get(SessionKeys.PASSWORD),
                        SessionKeys.EXPIRE_TIME, (new Date()).getTime() + expiresInMilliseconds);
		sessions.put(token, newInfo);
		return newInfo;
	}

	@Override
	public void removeSession(String token) {
		sessions.remove(token);

	}

	@Override
	public Enumeration<String> getTokens() {
		return sessions.keys();
	}
	
	private boolean isExpired(String token){
		ImmutableMap<SessionKeys, ? extends Object> info = sessions.get(token);
		if (info==null) return true;
		if (expiresInMilliseconds!=0 && (new Date()).getTime()>(Long)info.get(SessionKeys.EXPIRE_TIME)){
			removeSession(token);
			return true;
		}
		return false;
	}
	
	private void startSessionCleaner(long timeoutInMilliseconds) {
        sessionCleaner.schedule(task, 1000, timeoutInMilliseconds);
	}
}
