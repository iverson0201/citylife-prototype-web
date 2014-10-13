package com.citylife.backend.dto;

import java.util.Date;

import com.citylife.backend.domain.person.Pubulisher;

/**
 * @author 作者 E-mail:xujw0201@gmail.com
 * @version 创建时间：2014年10月13日 下午3:12:45
 */
public class TopicDto {
	
	private String id;
	private String title;
	private String content;
	/** 图片 */
	private String[] images;
	/** 发布人 */
	private Pubulisher pubulisher;
	private Date createdAt;
    private Date updatedAt;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
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
	public Pubulisher getPubulisher() {
		return pubulisher;
	}
	public void setPubulisher(Pubulisher pubulisher) {
		this.pubulisher = pubulisher;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public Date getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
    
}
