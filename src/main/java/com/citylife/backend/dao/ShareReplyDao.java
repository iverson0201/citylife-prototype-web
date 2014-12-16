package com.citylife.backend.dao;

import java.util.List;

import com.citylife.backend.domain.share.ShareReply;

/**
 * @author 作者 E-mail:xujw0201@gmail.com
 * @version 创建时间：2014年10月13日 下午5:02:50
 */
public interface ShareReplyDao extends BaseDao<ShareReply, String>{

	List<ShareReply> findByShareIdAndPage(String shareId, int size, int page);

	List<ShareReply> findByShareId(String shareId);

}
