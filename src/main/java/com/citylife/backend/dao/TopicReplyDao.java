package com.citylife.backend.dao;

import java.util.List;

import com.citylife.backend.domain.topic.TopicReply;

/**
 * @author 作者 E-mail:xujw0201@gmail.com
 * @version 创建时间：2014年10月13日 下午5:02:50
 */
public interface TopicReplyDao extends BaseDao<TopicReply, String>{

	List<TopicReply> findByTopic(String topicId, int size, int page);

	List<TopicReply> findByTopicId(String topicId);

}
