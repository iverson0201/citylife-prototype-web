package com.citylife.trackup.backend.domain.user;
/**
 * @author 作者 E-mail:xujw0201@gmail.com
 * @version 创建时间：2014年9月24日 上午9:41:16
 * 
 * 用户粉丝
 */
public class Follower {
	
	/** 用户的粉丝Id */
	private String followerId; 
	/** 用户的粉丝昵称 */
	private String followerName;
	
	
	public String getFollowerId() {
		return followerId;
	}
	public void setFollowerId(String followerId) {
		this.followerId = followerId;
	}
	public String getFollowerName() {
		return followerName;
	}
	public void setFollowerName(String followerName) {
		this.followerName = followerName;
	}
	
}
