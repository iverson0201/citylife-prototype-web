package com.citylife.backend.dto;

import java.util.Date;

import com.citylife.backend.domain.person.Backer;
import com.citylife.backend.domain.person.ReplyFollow;

/**
 * @author 作者 E-mail:xujw0201@gmail.com
 * @version 创建时间：2014年10月13日 下午5:07:02
 */
public class TopicReplyDto {

	private String id;
	/**回复内容 */
	private String replyContent;
	/**回复人 */
	private Backer backer;
	/**话题Id */
	private String topicId;
	/** 回复给谁，跟随 */
	private ReplyFollow replyFollow;
	
	private Date createdAt;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getReplyContent() {
		return replyContent;
	}
	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}
	public Backer getBacker() {
		return backer;
	}
	public void setBacker(Backer backer) {
		this.backer = backer;
	}
	public String getTopicId() {
		return topicId;
	}
	public void setTopicId(String topicId) {
		this.topicId = topicId;
	}
	public ReplyFollow getReplyFollow() {
		return replyFollow;
	}
	public void setReplyFollow(ReplyFollow replyFollow) {
		this.replyFollow = replyFollow;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	
}
