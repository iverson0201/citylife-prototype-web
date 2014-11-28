package com.citylife.backend.dao;

import java.util.List;

import com.citylife.backend.domain.poi.PoiComment;

/**
 * @author 作者 E-mail:xujw0201@gmail.com
 * @version 创建时间：2014年10月29日 上午9:26:17
 */
public interface PoiCommentDao extends BaseDao<PoiComment, String>{

	List<PoiComment> findList(String poiId, int size, int page);

	void updateComment(String commentId, int replyCount);

	List<PoiComment> findUserCommentList(String userId, int size, int page);

	long findAllUserCommentListCount(String userId);

	long findAllReplyListCount(String commentId);

	long allCommentCount(String poiId);

}
