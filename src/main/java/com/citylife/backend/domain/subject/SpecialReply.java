package com.citylife.backend.domain.subject;

import com.citylife.backend.domain.Base;
import com.citylife.backend.domain.person.Backer;

/**
 * @author 作者 E-mail:xujw0201@gmail.com
 * @version 创建时间：2014年9月28日 下午5:58:36
 * 
 * 专题回复
 */
public class SpecialReply extends Base{

	private String replyContent;
	private String[] images;
	private Backer backer;
	private String subjectId;
	
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
	public String getSubjectId() {
		return subjectId;
	}
	public void setSubjectId(String subjectId) {
		this.subjectId = subjectId;
	}
	
	
}
