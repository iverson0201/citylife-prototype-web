package com.citylife.backend.dao;

import java.util.List;

import com.citylife.backend.domain.share.Share;

/**
 * @author 作者 E-mail:xujw0201@gmail.com
 * @version 创建时间：2014年10月11日 下午4:54:55
 */
public interface ShareDao extends BaseDao<Share, String>{

	List<Share> findShares(int size, int page, String sort, String order);

	Share findSharePraise(String userId);

	Share findSharePraise(String shareId, String userId);

	List<Share> findSharesByUserId(String userId, Integer size, int page,
			String sort, String order);

	List<Share> findSharesByPoiId(String poiId, Integer size, int page,
			String sort, String order);

}
