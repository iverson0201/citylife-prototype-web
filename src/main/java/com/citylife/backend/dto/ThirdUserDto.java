package com.citylife.backend.dto;
/**
 * @author 作者 E-mail:xujw0201@gmail.com
 * @version 创建时间：2014年10月9日 下午5:56:10
 */
public class ThirdUserDto {

	private Integer thirdType;//1：QQ；2：微信；3：微博
	private Integer sex;//0：男；1：女
	private String name;
	private String headImage;
	public Integer getThirdType() {
		return thirdType;
	}
	public void setThirdType(Integer thirdType) {
		this.thirdType = thirdType;
	}
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getHeadImage() {
		return headImage;
	}
	public void setHeadImage(String headImage) {
		this.headImage = headImage;
	}
	
	
}
