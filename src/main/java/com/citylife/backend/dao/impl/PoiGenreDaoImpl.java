package com.citylife.backend.dao.impl;

import org.springframework.stereotype.Repository;

import com.citylife.backend.dao.PoiGenreDao;
import com.citylife.backend.domain.poi.PoiGenre;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

/**
 * @author 作者 E-mail:xujw0201@gmail.com
 * @version 创建时间：2014年12月2日 下午5:08:32
 */
@Repository
public class PoiGenreDaoImpl extends BaseDaoImpl<PoiGenre, String> implements PoiGenreDao {

	@Override
	public boolean existsPoiGenreByCode(String code) {
		// TODO Auto-generated method stub
		return mongoTemplate.exists(query(where("code").is(code)), PoiGenre.class);
	}

	@Override
	public PoiGenre findPoiGenreByCode(String parentCode) {
		// TODO Auto-generated method stub
		return mongoTemplate.findOne(query(where("code").is(parentCode)), PoiGenre.class);
	}

}
