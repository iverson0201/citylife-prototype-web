package com.citylife.backend.dao;

import java.util.List;

import com.citylife.backend.domain.business.BusinessComment;

/**
 * @author 作者 E-mail:xujw0201@gmail.com
 * @version 创建时间：2014年10月29日 上午9:26:17
 */
public interface BusinessCommentDao extends BaseDao<BusinessComment, String>{

	List<BusinessComment> findList(String businessId, int size, int page);

	void updateComment(String commentId, int replyCount);

	List<BusinessComment> findUserCommentList(String userId, int size, int page);

}
