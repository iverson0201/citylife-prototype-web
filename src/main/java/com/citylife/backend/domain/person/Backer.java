package com.citylife.backend.domain.person;
/**
 * @author 作者 E-mail:xujw0201@gmail.com
 * @version 创建时间：2014年9月28日 下午5:56:44
 * 
 * 回复人
 */
public class Backer {

	private String userId;
	private String userName;
	private String headImage;
	
	public Backer(String userId, String userName, String headImage) {
		this.userId = userId;
		this.userName = userName;
		this.headImage = headImage;
	}

	public Backer() {
	}


	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getHeadImage() {
		return headImage;
	}

	public void setHeadImage(String headImage) {
		this.headImage = headImage;
	}
	
}
