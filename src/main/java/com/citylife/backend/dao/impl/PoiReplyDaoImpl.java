package com.citylife.backend.dao.impl;

import static org.springframework.data.mongodb.core.query.Criteria.where;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.citylife.backend.dao.PoiReplyDao;
import com.citylife.backend.domain.poi.PoiReply;

/**
 * @author 作者 E-mail:xujw0201@gmail.com
 * @version 创建时间：2014年10月15日 下午4:50:57
 */
@Repository
public class PoiReplyDaoImpl extends BaseDaoImpl<PoiReply, String> implements PoiReplyDao {

	@Override
	public List<PoiReply> findList(String commentId, int size, int page) {
		// TODO Auto-generated method stub
		String sort = "updateAt";
		String order = "DESC";
		Query query = new Query();
		query.addCriteria(where("commentId").is(commentId));
		query.skip((page - 1) * size).limit(size);
        if (StringUtils.isNotBlank(sort) && StringUtils.isNotBlank(order)) {
        	 Sort.Direction direction = Sort.Direction.fromString(order);
             query.with(new Sort(direction, sort));
        }else{
        	query.with(new Sort(sort));
        }
		return mongoTemplate.find(query, PoiReply.class);
	}

}
