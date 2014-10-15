package com.citylife.backend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.citylife.backend.dao.BusinessReplyDao;
import com.citylife.backend.domain.business.BusinessReply;
import com.citylife.backend.service.BusinessReplyService;

/**
 * @author 作者 E-mail:xujw0201@gmail.com
 * @version 创建时间：2014年10月15日 下午4:49:15
 */
@Service
public class BusinessReplyServiceImpl implements BusinessReplyService {

	@Autowired
	private BusinessReplyDao businessReplyDao;
	@Override
	public void insert(BusinessReply businessReply) {
		// TODO Auto-generated method stub
		businessReplyDao.insert2Mongo(businessReply);
	}

}
