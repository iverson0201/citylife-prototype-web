package com.citylife.backend.dao.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.citylife.backend.dao.EnshrineDao;
import com.citylife.backend.domain.user.Enshrine;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

/**
 * @author 作者 E-mail:xujw0201@gmail.com
 * @version 创建时间：2014年10月16日 上午11:56:38
 */
@Repository
public class EnshrineDaoImpl extends BaseDaoImpl<Enshrine, String> implements EnshrineDao {

	@Override
	public Enshrine findEnshrine(String id, int status) {
		// TODO Auto-generated method stub
		return mongoTemplate.findOne(query(where("_id").is(id).and("status").is(status)), Enshrine.class);
	}

	@Override
	public Enshrine findByPoiId(String id) {
		// TODO Auto-generated method stub
		return mongoTemplate.findOne(query(where("poiId").is(id)), Enshrine.class);
	}

	@Override
	public Enshrine findEnshrineByParam(String poiId, String userId) {
		// TODO Auto-generated method stub
		return mongoTemplate.findOne(query(where("poiId").is(poiId).and("userId").is(userId)), Enshrine.class);
	}

	@Override
	public List<Enshrine> getPage(String userId, Integer size, Integer page,
			String sort, String order) {
		// TODO Auto-generated method stub
		Query query = new Query();
		query.addCriteria(where("userId").is(userId));
		query.skip((page - 1) * size).limit(size);
        if (StringUtils.isNotBlank(sort) && StringUtils.isNotBlank(order)) {
        	 Sort.Direction direction = Sort.Direction.fromString(order);
             query.with(new Sort(direction, sort));
        }else{
        	query.with(new Sort(sort));
        }
		return mongoTemplate.find(query, Enshrine.class);
	}

}
