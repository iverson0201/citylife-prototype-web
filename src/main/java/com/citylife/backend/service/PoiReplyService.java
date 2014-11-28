package com.citylife.backend.service;

import java.util.List;

import com.citylife.backend.domain.poi.PoiComment;
import com.citylife.backend.domain.poi.PoiReply;

/**
 * @author 作者 E-mail:xujw0201@gmail.com
 * @version 创建时间：2014年10月15日 下午4:48:58
 */
public interface PoiReplyService {

	void insert(PoiReply poiReply);

	void createComment(PoiComment poiComment);

	List<PoiComment> getCommentList(String poiId, int size, int page);

	List<PoiReply> getReplyList(String commentId, int size, int page);

	PoiComment getComment(String commentId);

	void updateComment(String commentId, int replyCount);

	List<PoiComment> getUserCommentList(String userId, int size, int page);

	long getAllUserCommentListCount(String userId);

	long getAllReplyListCount(String commentId);

	long getAllCommentCount(String poiId);

}
