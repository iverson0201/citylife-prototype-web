package com.citylife.backend.domain.poi;

import com.citylife.backend.domain.Base;
import com.citylife.backend.domain.person.Backer;
import com.citylife.backend.domain.person.ReplyFollow;

/**
 * @author 作者 E-mail:xujw0201@gmail.com
 * @version 创建时间：2014年10月15日 下午4:40:30
 * 商家回复
 */
public class PoiReply extends Base{

	private String content;
	private Backer backer;
	private ReplyFollow replyFollow;
	private String commentId;
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
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
	public String getCommentId() {
		return commentId;
	}
	public void setCommentId(String commentId) {
		this.commentId = commentId;
	}
	
}
