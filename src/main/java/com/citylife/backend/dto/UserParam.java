package com.citylife.backend.dto;

import com.citylife.backend.domain.user.User;

/**
 * @author 作者 E-mail:xujw0201@gmail.com
 * @version 创建时间：2014年9月24日 下午5:58:47
 */
public class UserParam {

	private String followId;
	private String followName;
	private User user;
	private Boolean flag;
	
	
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
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Boolean getFlag() {
		return flag;
	}
	public void setFlag(Boolean flag) {
		this.flag = flag;
	}
	
	
}
