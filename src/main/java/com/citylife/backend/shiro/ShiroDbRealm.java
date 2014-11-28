/*******************************************************************************
 * Copyright (c) 2005, 2014 springside.github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package com.citylife.backend.shiro;

import javax.annotation.PostConstruct;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.citylife.backend.service.UserService;

public class ShiroDbRealm extends AuthorizingRealm {

	public static final String HASH_ALGORITHM = "SHA-1";
	public static final int HASH_INTERATIONS = 1024;
	@Autowired
    private UserService userService;

	/**
	 * 认证回调函数,登录时调用.
	 */
	@Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {  
        //token中储存着输入的用户名和密码  
        UsernamePasswordToken upToken = (UsernamePasswordToken)token;  
        //获得用户名与密码  
        String tel = upToken.getUsername();
        String password = String.valueOf(upToken.getPassword());  
        //TODO 与数据库中用户名和密码进行比对。比对成功则返回info，比对失败则抛出对应信息的异常AuthenticationException  
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(tel, password .toCharArray(),getName());
        System.out.println("认证回调函数,登录时调用.");
        return info;  
    }  

	/**
	 * 授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用.
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
//		ShiroUser shiroUser = (ShiroUser) principals.getPrimaryPrincipal();
//		User user = userService.findByUserName(shiroUser.loginName);
//		String userName = (String) getAvailablePrincipal(principals);  
	    //TODO 通过用户名获得用户的所有资源，并把资源存入info中  
//	    SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();  
//	    info.setStringPermissions(set集合);  
//	    info.setRoles(set集合);  
//	    info.setObjectPermissions(set集合);  
//		System.out.println(userName + " : aaaaa: " + user.getUsername());
		System.out.println("授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用.");
		return null;
	}

	/**
	 * 设定Password校验的Hash算法与迭代次数.
	 */
	@PostConstruct
	public void initCredentialsMatcher() {
//		HashedCredentialsMatcher matcher = new HashedCredentialsMatcher(HASH_ALGORITHM);
//		matcher.setHashIterations(HASH_INTERATIONS);
//		setCredentialsMatcher(matcher);
		System.out.println("设定Password校验的Hash算法与迭代次数.");
	}

	
}
