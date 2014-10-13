package com.citylife.backend.domain.user;
/**
 * @author 作者 E-mail:xujw0201@gmail.com
 * @version 创建时间：2014年10月11日 下午6:01:04
 */
public class ThirdUser {
	
	private String thirdId;
	private int thirdType;//1:QQ；2:微信；3：微博
	
	
	public ThirdUser() {
	}
	public ThirdUser(String thirdId, int thirdType) {
		super();
		this.thirdId = thirdId;
		this.thirdType = thirdType;
	}
	public String getThirdId() {
		return thirdId;
	}
	public void setThirdId(String thirdId) {
		this.thirdId = thirdId;
	}
	public int getThirdType() {
		return thirdType;
	}
	public void setThirdType(int thirdType) {
		this.thirdType = thirdType;
	}
	
}
