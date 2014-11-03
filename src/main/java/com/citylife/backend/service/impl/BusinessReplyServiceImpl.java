package com.citylife.backend.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.citylife.backend.dao.BusinessCommentDao;
import com.citylife.backend.dao.BusinessReplyDao;
import com.citylife.backend.domain.business.BusinessComment;
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
	@Autowired
	private BusinessCommentDao businessCommentDao;
	@Override
	public void insert(BusinessReply businessReply) {
		// TODO Auto-generated method stub
		businessReplyDao.insert2Mongo(businessReply);
	}
	@Override
	public void createComment(BusinessComment businessComment) {
		// TODO Auto-generated method stub
		businessCommentDao.insert2Mongo(businessComment);
	}
	@Override
	public List<BusinessComment> getCommentList(String businessId, int size, int page) {
		// TODO Auto-generated method stub
		return businessCommentDao.findList(businessId,size,page);
	}
	@Override
	public List<BusinessReply> getReplyList(String commentId, int size, int page) {
		// TODO Auto-generated method stub
		return businessReplyDao.findList(commentId,size,page);
	}
	@Override
	public BusinessComment getComment(String commentId) {
		// TODO Auto-generated method stub
		return businessCommentDao.findByIdFromMongo(commentId);
	}
	@Override
	public void updateComment(String commentId, int replyCount) {
		// TODO Auto-generated method stub
		businessCommentDao.updateComment(commentId, replyCount);
	}
	@Override
	public List<BusinessComment> getUserCommentList(String userId, int size,
			int page) {
		// TODO Auto-generated method stub
		return businessCommentDao.findUserCommentList(userId,size,page);
	}

}
