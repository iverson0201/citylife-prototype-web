package com.citylife.backend.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.citylife.backend.dao.ShareReplyDao;
import com.citylife.backend.domain.share.ShareReply;
import com.citylife.backend.service.ShareReplyService;

/**
 * @author 作者 E-mail:xujw0201@gmail.com
 * @version 创建时间：2014年10月13日 下午5:01:47
 */
@Service
public class ShareReplyServiceImpl implements ShareReplyService {

	@Autowired
	private ShareReplyDao shareReplyDao;

	@Override
	public void insert(ShareReply shareReply) {
		// TODO Auto-generated method stub
		shareReplyDao.insert2Mongo(shareReply);
	}

	@Override
	public void deleteReply(String replyId) {
		// TODO Auto-generated method stub
		shareReplyDao.delete2Mongo(replyId);
	}

	@Override
	public List<ShareReply> getShareReplys(String shareId, int size, int page) {
		// TODO Auto-generated method stub
		return shareReplyDao.findByShareIdAndPage(shareId,size,page);
	}

	@Override
	public List<ShareReply> getShareReplys(String shareId) {
		// TODO Auto-generated method stub
		return shareReplyDao.findByShareId(shareId);
	}
}
