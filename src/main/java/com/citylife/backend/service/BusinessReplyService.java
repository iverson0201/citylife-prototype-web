package com.citylife.backend.service;

import java.util.List;

import com.citylife.backend.domain.business.BusinessComment;
import com.citylife.backend.domain.business.BusinessReply;

/**
 * @author 作者 E-mail:xujw0201@gmail.com
 * @version 创建时间：2014年10月15日 下午4:48:58
 */
public interface BusinessReplyService {

	void insert(BusinessReply businessReply);

	void createComment(BusinessComment businessComment);

	List<BusinessComment> getCommentList(String businessId, int size, int page);

	List<BusinessReply> getReplyList(String commentId, int size, int page);

	BusinessComment getComment(String commentId);

	void updateComment(String commentId, int replyCount);

	List<BusinessComment> getUserCommentList(String userId, int size, int page);

}
