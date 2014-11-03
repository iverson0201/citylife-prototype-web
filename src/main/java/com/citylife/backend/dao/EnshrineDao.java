package com.citylife.backend.dao;

import java.util.List;

import com.citylife.backend.domain.user.Enshrine;

/**
 * @author 作者 E-mail:xujw0201@gmail.com
 * @version 创建时间：2014年10月16日 上午11:56:25
 */
public interface EnshrineDao extends BaseDao<Enshrine, String>{

	Enshrine findEnshrine(String id, int status);

	Enshrine findByPoiId(String id);

	Enshrine findEnshrineByParam(String poiId, String userId);

	List<Enshrine> getPage(String userId, Integer size, Integer page,
			String sort, String order);

}
