package com.citylife.trackup.backend.dto;

import com.citylife.trackup.backend.domain.person.Pubulisher;

/**
 * @author 作者 E-mail:xujw0201@gmail.com
 * @version 创建时间：2014年9月24日 上午11:56:05
 */
public class SubjectDto {

	private String id;
	/** 专题名称 */
	private String title;
	/** 专题内容 */
	private String content;
	/** 图片 */
	private String[] images;
	/** 发布人 */
	private Pubulisher pubulisher;
	
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
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Pubulisher getPubulisher() {
		return pubulisher;
	}
	public void setPubulisher(Pubulisher pubulisher) {
		this.pubulisher = pubulisher;
	}
}
