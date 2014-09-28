package com.citylife.trackup.backend.auth;

public enum SessionKeys {
	TOKEN ("X-SESSION"),
	APP_CODE ("X-APPCODE"),
	USERNAME ("USERNAME"),
	PASSWORD ("PASSWORD"),
	START_TIME ("START_TIME"),
	EXPIRE_TIME ("EXPIRE_TIME");
	
	private String key;
	private SessionKeys(String key) {
	    this.key = key; 
	} 
	
	@Override
	public String toString(){
	    return key; 
	} 
}