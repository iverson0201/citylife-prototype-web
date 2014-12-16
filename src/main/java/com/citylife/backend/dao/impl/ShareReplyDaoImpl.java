package com.citylife.backend.dao.impl;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.citylife.backend.dao.ShareReplyDao;
import com.citylife.backend.domain.share.ShareReply;

/**
 * @author 作者 E-mail:xujw0201@gmail.com
 * @version 创建时间：2014年10月13日 下午5:03:03
 */
@Repository
public class ShareReplyDaoImpl extends BaseDaoImpl<ShareReply, String> implements ShareReplyDao {

	@Override
	public List<ShareReply> findByShareIdAndPage(String shareId, int size, int page) {
		// TODO Auto-generated method stub
		String sort = "updateAt";
		String order = "DESC";
		Query query = new Query();
		query.addCriteria(where("shareId").is(shareId));
		query.skip((page - 1) * size).limit(size);
        if (StringUtils.isNotBlank(sort) && StringUtils.isNotBlank(order)) {
        	 Sort.Direction direction = Sort.Direction.fromString(order);
             query.with(new Sort(direction, sort));
        }else{
        	query.with(new Sort(sort));
        }
		return mongoTemplate.find(query, ShareReply.class);
	}

	@Override
	public List<ShareReply> findByShareId(String shareId) {
		// TODO Auto-generated method stub
		return mongoTemplate.find(query(where("shareId").is(shareId)), ShareReply.class);
	}

}
