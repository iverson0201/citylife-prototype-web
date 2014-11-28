package com.citylife.backend.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.citylife.backend.dao.PoiCommentDao;
import com.citylife.backend.dao.PoiReplyDao;
import com.citylife.backend.domain.poi.PoiComment;
import com.citylife.backend.domain.poi.PoiReply;
import com.citylife.backend.service.PoiReplyService;

/**
 * @author 作者 E-mail:xujw0201@gmail.com
 * @version 创建时间：2014年10月15日 下午4:49:15
 */
@Service
public class PoiReplyServiceImpl implements PoiReplyService {

	@Autowired
	private PoiReplyDao poiReplyDao;
	@Autowired
	private PoiCommentDao poiCommentDao;
	@Override
	public void insert(PoiReply poiReply) {
		// TODO Auto-generated method stub
		poiReplyDao.insert2Mongo(poiReply);
	}
	@Override
	public void createComment(PoiComment poiComment) {
		// TODO Auto-generated method stub
		poiCommentDao.insert2Mongo(poiComment);
	}
	@Override
	public List<PoiComment> getCommentList(String poiId, int size, int page) {
		// TODO Auto-generated method stub
		return poiCommentDao.findList(poiId,size,page);
	}
	@Override
	public List<PoiReply> getReplyList(String commentId, int size, int page) {
		// TODO Auto-generated method stub
		return poiReplyDao.findList(commentId,size,page);
	}
	@Override
	public PoiComment getComment(String commentId) {
		// TODO Auto-generated method stub
		return poiCommentDao.findByIdFromMongo(commentId);
	}
	@Override
	public void updateComment(String commentId, int replyCount) {
		// TODO Auto-generated method stub
		poiCommentDao.updateComment(commentId, replyCount);
	}
	@Override
	public List<PoiComment> getUserCommentList(String userId, int size,
			int page) {
		// TODO Auto-generated method stub
		return poiCommentDao.findUserCommentList(userId,size,page);
	}
	@Override
	public long getAllUserCommentListCount(String userId) {
		// TODO Auto-generated method stub
		return poiCommentDao.findAllUserCommentListCount(userId);
	}
	@Override
	public long getAllReplyListCount(String commentId) {
		// TODO Auto-generated method stub
		return poiCommentDao.findAllReplyListCount(commentId);
	}
	@Override
	public long getAllCommentCount(String poiId) {
		// TODO Auto-generated method stub
		return poiCommentDao.allCommentCount(poiId);
	}

}
