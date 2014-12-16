package com.citylife.backend.dto;

import java.util.Date;

import com.citylife.backend.domain.person.Backer;
import com.citylife.backend.domain.person.ReplyFollow;

/**
 * @author 作者 E-mail:xujw0201@gmail.com
 * @version 创建时间：2014年10月13日 下午5:07:02
 */
public class ShareReplyDto {

	private String id;
	/**回复内容 */
	private String replyContent;
	/**回复人 */
	private Backer backer;
	/**分享Id */
	private String shareId;
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
	public String getShareId() {
		return shareId;
	}
	public void setShareId(String shareId) {
		this.shareId = shareId;
	}
	
}
