package com.citylife.backend.dao.impl;

import static org.springframework.data.mongodb.core.query.Criteria.where;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.citylife.backend.dao.TopicDao;
import com.citylife.backend.domain.topic.Topic;

/**
 * @author 作者 E-mail:xujw0201@gmail.com
 * @version 创建时间：2014年10月11日 下午4:55:08
 */
@Repository
public class TopicDaoImpl extends BaseDaoImpl<Topic, String> implements TopicDao {
	
	@Value("#{configs['topic.hot.maxhours']}")
    private Integer topicHotMaxHours;
	
	@Resource(name = "mongoTemplate")
	private MongoTemplate mongoTemplate;
	
	@Override
	public List<Topic> findTopics(int size, int page, String sort, String order) {
		// TODO Auto-generated method stub
		Query query = new Query();
		Date time = hotMaxHours();
        query.addCriteria(where("createdAt").gt(time));
        
        query.skip((page - 1) * size).limit(size);
        if (StringUtils.isNotBlank(sort) && StringUtils.isNotBlank(order)) {
        	 Sort.Direction direction = Sort.Direction.fromString(order);
             query.with(new Sort(direction, sort));
        }else{
        	query.with(new Sort(sort));
        }
        
		return mongoTemplate.find(query, Topic.class);
	}

	private Date hotMaxHours() {
		Calendar c = Calendar.getInstance();
        Date date = new Date();
        c.setTime(date);
        int day = c.get(Calendar.HOUR);
        c.set(Calendar.HOUR, day - topicHotMaxHours);
        Date time = c.getTime();
		return time;
	}

}
