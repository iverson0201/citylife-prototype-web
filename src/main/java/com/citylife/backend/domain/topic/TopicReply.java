package com.citylife.backend.domain.topic;

import com.citylife.backend.domain.Base;
import com.citylife.backend.domain.person.Backer;
import com.citylife.backend.domain.person.ReplyFollow;

/**
 * @author 作者 E-mail:xujw0201@gmail.com
 * @version 创建时间：2014年10月13日 下午3:54:56
 */
public class TopicReply extends Base{

	/**回复内容 */
	private String replyContent;
	/**图片 */
	private String[] images;
	/**回复人 */
	private Backer backer;
	/**话题Id */
	private String topicId;
	/** 回复给谁，跟随 */
	private ReplyFollow replyFollow;
	
	
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
	
}
