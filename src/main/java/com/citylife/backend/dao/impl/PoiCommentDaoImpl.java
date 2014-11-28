package com.citylife.backend.dao.impl;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.citylife.backend.dao.PoiCommentDao;
import com.citylife.backend.domain.poi.PoiComment;
import com.citylife.backend.domain.poi.PoiReply;

/**
 * @author 作者 E-mail:xujw0201@gmail.com
 * @version 创建时间：2014年10月29日 上午9:26:49
 */
@Repository
public class PoiCommentDaoImpl extends BaseDaoImpl<PoiComment, String> implements PoiCommentDao {
	 
	@Override
	public List<PoiComment> findList(String poiId, int size, int page) {
		// TODO Auto-generated method stub
		String sort = "updateAt";
		String order = "DESC";
		Query query = new Query();
		query.addCriteria(where("poiId").is(poiId));
		query.skip((page - 1) * size).limit(size);
        if (StringUtils.isNotBlank(sort) && StringUtils.isNotBlank(order)) {
        	 Sort.Direction direction = Sort.Direction.fromString(order);
             query.with(new Sort(direction, sort));
        }else{
        	query.with(new Sort(sort));
        }
		return mongoTemplate.find(query, PoiComment.class);
	}

	@Override
	public void updateComment(String commentId, int replyCount) {
		// TODO Auto-generated method stub
		Update update = new Update();
		update.set("replyCount", replyCount);
		mongoTemplate.updateFirst(query(where("_id").is(commentId)), update, PoiComment.class);
	}

	@Override
	public List<PoiComment> findUserCommentList(String userId, int size,
			int page) {
		// TODO Auto-generated method stub
		String sort = "updateAt";
		String order = "DESC";
		Query query = new Query();
		query.addCriteria(where("comment.userId").is(userId));
		query.skip((page - 1) * size).limit(size);
        if (StringUtils.isNotBlank(sort) && StringUtils.isNotBlank(order)) {
        	 Sort.Direction direction = Sort.Direction.fromString(order);
             query.with(new Sort(direction, sort));
        }else{
        	query.with(new Sort(sort));
        }
		return mongoTemplate.find(query, PoiComment.class);
	}

	@Override
	public long findAllUserCommentListCount(String userId) {
		// TODO Auto-generated method stub
		return mongoTemplate.count(query(where("comment.userId").is(userId)), PoiComment.class);
	}

	@Override
	public long findAllReplyListCount(String commentId) {
		// TODO Auto-generated method stub
		return mongoTemplate.count(query(where("commentId").is(commentId)), PoiReply.class);
	}

	@Override
	public long allCommentCount(String poiId) {
		// TODO Auto-generated method stub
		return mongoTemplate.count(query(where("poiId").is(poiId)), PoiComment.class);
	}

}
