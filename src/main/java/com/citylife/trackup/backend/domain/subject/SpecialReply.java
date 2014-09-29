package com.citylife.trackup.backend.domain.subject;

import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Field;

import com.citylife.trackup.backend.domain.person.Backer;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author 作者 E-mail:xujw0201@gmail.com
 * @version 创建时间：2014年9月28日 下午5:58:36
 * 
 * 专题回复
 */
public class SpecialReply {

	private String Id;
	private String replyContent;
	private String[] images;
	private Backer backer;
	@JsonProperty("created_at")
    @Field("created_at")
    private Date createdAt;
	
	public String getReplyContent() {
		return replyContent;
	}
	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}
	public String[] getImages() {
		return images;
	}
	public void setImages(String[] images) {
		this.images = images;
	}
	public Backer getBacker() {
		return backer;
	}
	public void setBacker(Backer backer) {
		this.backer = backer;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public String getId() {
		return Id;
	}
	public void setId(String id) {
		Id = id;
	}
	
	
}
