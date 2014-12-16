package com.citylife.backend.dao.impl;

import org.springframework.stereotype.Repository;

import com.citylife.backend.dao.PoiInfoDao;
import com.citylife.backend.domain.poi.PoiInfo;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;
/**
 * @author 作者 E-mail:xujw0201@gmail.com
 * @version 创建时间：2014年12月3日 下午5:35:19
 */
@Repository
public class PoiInfoDaoImpl extends BaseDaoImpl<PoiInfo, String> implements PoiInfoDao {

	@Override
	public boolean existPoiInfo(String poiId) {
		// TODO Auto-generated method stub
		return mongoTemplate.exists(query(where("poiId").is(poiId)), PoiInfo.class);
	}

	@Override
	public PoiInfo findByPoiId(String poiId) {
		// TODO Auto-generated method stub
		return mongoTemplate.findOne(query(where("poiId").is(poiId)), PoiInfo.class);
	}

}
