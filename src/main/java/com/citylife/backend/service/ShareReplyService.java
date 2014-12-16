package com.citylife.backend.service;

import java.util.List;

import com.citylife.backend.domain.share.ShareReply;

/**
 * @author 作者 E-mail:xujw0201@gmail.com
 * @version 创建时间：2014年10月13日 下午5:01:33
 */
public interface ShareReplyService {

	void insert(ShareReply ShareReply);

	void deleteReply(String replyId);

	List<ShareReply> getShareReplys(String shareId, int size, int page);

	List<ShareReply> getShareReplys(String shareId);

}
