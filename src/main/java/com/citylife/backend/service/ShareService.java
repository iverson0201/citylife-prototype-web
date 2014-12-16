package com.citylife.backend.service;

import java.util.List;

import com.citylife.backend.domain.share.Share;

/**
 * @author 作者 E-mail:xujw0201@gmail.com
 * @version 创建时间：2014年10月11日 下午4:48:09
 */
public interface ShareService {

	void insertShare(Share share);

	Share getShare(String shareId);

	Share updateShare(String id, Share share);

	void deleteShare(String shareId);

	List<Share> getShares(int size, int page, String sort, String order);

	Share getShareByPraise(String userId);

	Share getShare(String shareId, String userId);

	List<Share> getSharesByUserId(String userId, Integer size, int page, String sort,
			String order);

	List<Share> getSharesByPoiId(String poiId, Integer size, int page,
			String sort, String order);

}
