package com.citylife.backend.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.citylife.backend.dao.ShareDao;
import com.citylife.backend.domain.share.Share;
import com.citylife.backend.service.ShareService;

/**
 * @author 作者 E-mail:xujw0201@gmail.com
 * @version 创建时间：2014年10月11日 下午4:48:20
 */
@Service
public class ShareServiceImpl implements ShareService {

	@Autowired
	private ShareDao shareDao;
	@Override
	public void insertShare(Share share) {
		// TODO Auto-generated method stub
		shareDao.insert2Mongo(share);
	}
	@Override
	public Share getShare(String shareId) {
		// TODO Auto-generated method stub
		return shareDao.findByIdFromMongo(shareId);
	}
	@Override
	public Share updateShare(String id, Share share) {
		// TODO Auto-generated method stub
		return shareDao.update2Mongo(id, share);
	}
	@Override
	public void deleteShare(String shareId) {
		// TODO Auto-generated method stub
		shareDao.delete2Mongo(shareId);
	}
	@Override
	public List<Share> getShares(int size, int page, String sort, String order) {
		// TODO Auto-generated method stub
		return shareDao.findShares(size,page,sort,order);
	}
	@Override
	public Share getShareByPraise(String userId) {
		// TODO Auto-generated method stub
		return shareDao.findSharePraise(userId);
	}
	@Override
	public Share getShare(String shareId, String userId) {
		// TODO Auto-generated method stub
		return shareDao.findSharePraise(shareId,userId);
	}
	@Override
	public List<Share> getSharesByUserId(String userId, Integer size, int page,
			String sort, String order) {
		// TODO Auto-generated method stub
		return shareDao.findSharesByUserId(userId,size,page,sort,order);
	}
	@Override
	public List<Share> getSharesByPoiId(String poiId, Integer size, int page,
			String sort, String order) {
		// TODO Auto-generated method stub
		return shareDao.findSharesByPoiId(poiId,size,page,sort,order);
	}

}
