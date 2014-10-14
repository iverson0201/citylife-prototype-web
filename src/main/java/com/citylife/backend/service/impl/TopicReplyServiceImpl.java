package com.citylife.backend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.citylife.backend.dao.TopicReplyDao;
import com.citylife.backend.domain.topic.TopicReply;
import com.citylife.backend.service.TopicReplyService;

/**
 * @author 作者 E-mail:xujw0201@gmail.com
 * @version 创建时间：2014年10月13日 下午5:01:47
 */
@Service
public class TopicReplyServiceImpl implements TopicReplyService {

	@Autowired
	private TopicReplyDao topicReplyDao;

	@Override
	public void insert(TopicReply topicReply) {
		// TODO Auto-generated method stub
		topicReplyDao.insert2Mongo(topicReply);
	}

	@Override
	public void deleteReply(String replyId) {
		// TODO Auto-generated method stub
		topicReplyDao.delete2Mongo(replyId);
	}
}
