package com.citylife.backend.domain.user;
/**
 * @author 作者 E-mail:xujw0201@gmail.com
 * @version 创建时间：2014年9月24日 上午9:31:46
 * 
 * 用户关注 
 */
public class Follow{
	
	/** 关注的用户Id */
	private String followId;
	/** 关注的用户昵称 */
	private String followName;
	
	public String getFollowId() {
		return followId;
	}
	public void setFollowId(String followId) {
		this.followId = followId;
	}
	public String getFollowName() {
		return followName;
	}
	public void setFollowName(String followName) {
		this.followName = followName;
	}
	
}
