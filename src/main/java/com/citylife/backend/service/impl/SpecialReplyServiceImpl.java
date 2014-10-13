package com.citylife.backend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.citylife.backend.dao.SpecialReplyDao;
import com.citylife.backend.domain.subject.SpecialReply;
import com.citylife.backend.service.SpecialReplyService;

/**
 * @author 作者 E-mail:xujw0201@gmail.com
 * @version 创建时间：2014年9月29日 下午5:50:43
 */
@Service
public class SpecialReplyServiceImpl implements SpecialReplyService {

	@Autowired
	private SpecialReplyDao specialReplyDao = new SpecialReplyDaoImpl();

	@Override
	public void save(SpecialReply specialReply) {
		// TODO Auto-generated method stub
		specialReplyDao.save2Mongo(specialReply);
	}

	@Override
	public void deleteReply(String specialReplyId) {
		// TODO Auto-generated method stub
		specialReplyDao.delete2Mongo(specialReplyId);
	}

	@Override
	public boolean findReply(String specialReplyId) {
		// TODO Auto-generated method stub
		SpecialReply reply = specialReplyDao.findByIdFromMongo(specialReplyId);
		return reply != null;
	}
}
