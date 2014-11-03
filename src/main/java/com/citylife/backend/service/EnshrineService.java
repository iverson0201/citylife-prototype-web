package com.citylife.backend.service;

import java.util.List;

import com.citylife.backend.domain.user.Enshrine;

/**
 * @author 作者 E-mail:xujw0201@gmail.com
 * @version 创建时间：2014年10月16日 上午11:55:34
 */
public interface EnshrineService {

	void create(Enshrine enshrine);

	void delete(String enshrineId);

	List<Enshrine> getEnshrines(Integer size, Integer page, String sort,
			String order);

	Enshrine findEnshrine(String id, int status);

	Enshrine findEnshrine(String id);

	Enshrine findEnshrineByPoiId(String id);

	Enshrine findEnshrineByParam(String poiId, String userId);

	void update(Enshrine enshrineRet);

	List<Enshrine> getEnshrinesPage(String userId, Integer size, Integer page,
			String sort, String order);

}
