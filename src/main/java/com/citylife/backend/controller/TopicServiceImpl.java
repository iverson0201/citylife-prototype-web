package com.citylife.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.citylife.backend.dao.TopicDao;
import com.citylife.backend.domain.topic.Topic;
import com.citylife.backend.service.TopicService;

/**
 * @author 作者 E-mail:xujw0201@gmail.com
 * @version 创建时间：2014年10月11日 下午4:48:20
 */
@Service
public class TopicServiceImpl implements TopicService {

	@Autowired
	private TopicDao topicDao;
	@Override
	public void insertTopic(Topic topic) {
		// TODO Auto-generated method stub
		topicDao.insert2Mongo(topic);
	}

}
