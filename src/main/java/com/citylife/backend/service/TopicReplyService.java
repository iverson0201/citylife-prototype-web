package com.citylife.backend.service;

import java.util.List;

import com.citylife.backend.domain.topic.TopicReply;

/**
 * @author 作者 E-mail:xujw0201@gmail.com
 * @version 创建时间：2014年10月13日 下午5:01:33
 */
public interface TopicReplyService {

	void insert(TopicReply topicReply);

	void deleteReply(String replyId);

	List<TopicReply> getTopicReplys(String topicId, int size, int page);

	List<TopicReply> getTopicReplys(String topicId);

}
