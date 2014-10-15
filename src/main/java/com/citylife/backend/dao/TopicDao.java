package com.citylife.backend.dao;

import java.util.List;

import com.citylife.backend.domain.topic.Topic;

/**
 * @author 作者 E-mail:xujw0201@gmail.com
 * @version 创建时间：2014年10月11日 下午4:54:55
 */
public interface TopicDao extends BaseDao<Topic, String>{

	List<Topic> findTopics(int size, int page, String sort, String order);

}
