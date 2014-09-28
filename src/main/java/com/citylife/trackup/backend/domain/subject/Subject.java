package com.citylife.trackup.backend.domain.subject;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.citylife.trackup.backend.domain.Base;

/**
 * @author 作者 E-mail:xujw0201@gmail.com
 * @version 创建时间：2014年9月24日 上午11:44:45
 */
public class Subject extends Base {

	/** 专题名称 */
	private String title;
	/** 专题内容 */
	private String content;
	/** 图片 */
	private String[] images;
	/** 用户Id */
	private String userId;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String[] getImages() {
		return images;
	}
	public void setImages(String[] images) {
		this.images = images;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	
	
}
