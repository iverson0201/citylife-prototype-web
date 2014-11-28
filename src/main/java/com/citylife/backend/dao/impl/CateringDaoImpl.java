package com.citylife.backend.dao.impl;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

import org.springframework.stereotype.Repository;

import com.citylife.backend.dao.CateringDao;
import com.citylife.backend.domain.poi.catering.Catering;

/**
 * @author 作者 E-mail:xujw0201@gmail.com
 * @version 创建时间：2014年10月15日 下午1:49:59
 */
@Repository
public class CateringDaoImpl extends BaseDaoImpl<Catering, String> implements CateringDao {

	@Override
	public Catering findByPoiId(String poiId) {
		// TODO Auto-generated method stub
		return mongoTemplate.findOne(query(where("poiId").is(poiId)), Catering.class);
	}

}
