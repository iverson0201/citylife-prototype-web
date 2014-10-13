package com.citylife.backend.service;

import com.citylife.backend.domain.topic.Topic;

/**
 * @author 作者 E-mail:xujw0201@gmail.com
 * @version 创建时间：2014年10月11日 下午4:48:09
 */
public interface TopicService {

	void insertTopic(Topic topic);

	Topic getTopic(String topicId);

	Topic updateTopic(String id, Topic topic);

	void deleteTopic(String topicId);

}
