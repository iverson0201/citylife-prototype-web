package com.citylife.backend.domain.share;

import com.citylife.backend.domain.Base;
import com.citylife.backend.domain.person.Backer;
import com.citylife.backend.domain.person.ReplyFollow;

/**
 * @author 作者 E-mail:xujw0201@gmail.com
 * @version 创建时间：2014年10月13日 下午3:54:56
 * 分享评论
 */
public class ShareReply extends Base{

	/**回复内容 */
	private String replyContent;
	/**回复人 */
	private Backer backer;
	/**分享Id */
	private String shareId;
	/** 回复给谁，跟随 */
	private ReplyFollow replyFollow;
	
	
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
	public String getShareId() {
		return shareId;
	}
	public void setShareId(String shareId) {
		this.shareId = shareId;
	}
	
}
