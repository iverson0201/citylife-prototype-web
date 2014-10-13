package com.citylife.backend.dao.impl;

import javax.annotation.Resource;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import com.citylife.backend.dao.QiniuDao;
import com.citylife.backend.domain.qiniu.Qiniu;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

/**
 * @author 作者 E-mail:xujw0201@gmail.com
 * @version 创建时间：2014年9月26日 下午5:07:50
 */
@Repository
public class QiniuDaoImpl extends BaseDaoImpl<Qiniu, String> implements QiniuDao {

	@Resource(name = "mongoTemplate")
	private MongoTemplate mongoTemplate;
	@Override
	public boolean findByScope(String scope) {
		// TODO Auto-generated method stub
//		return mongoTemplate.findOne(query(where("scope").is(scope)), Qiniu.class)
		return mongoTemplate.exists(query(where("scope").is(scope)), Qiniu.class);
	}

}
