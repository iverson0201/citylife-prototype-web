package com.citylife.trackup.backend.dao.impl;

import java.util.List;

import com.citylife.trackup.backend.dao.UserDao;
import com.citylife.trackup.backend.domain.user.User;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@Repository
public class UserDaoImpl extends BaseDaoImpl<User, String> implements
        UserDao {

    @Resource(name = "mongoTemplate")
    private MongoTemplate mongoTemplate;
    @Value("#{configs['user.follower.max']}")
    private Integer maxfollowers;

    @Override
    public User findByUserNameFromMongo(String name) {
        return mongoTemplate.findOne(query(where("username").is(name)), User.class);
    }

    @Override
    public User findByPhoneNumFromMongo(String num) {
        return mongoTemplate.findOne(query(where("tel").is(num)), User.class);
    }

    @Override
    public User findByPhoneNumAndPwdFromMongo(String num, String password) {
        return mongoTemplate.findOne(query(
                where("tel").is(num).and("password").is(password)),
                User.class);
    }

    @Override
    public User login(User user) {

        return mongoTemplate.findOne(query(where("tel").is(user.getTel()).and("password").is(user.getPassword())), User.class);
    }

	@Override
	public List<User> findAllByFields(Query query) {
		query.with(new Sort(Sort.Direction.DESC, "updated_at"));
		return mongoTemplate.find(query(where("role").is("2")), User.class);
	}
    @Override
    public User testSQL(String name)
    {
        return mongoTemplate.findOne(query(where("tel").is(name)), User.class);
    }
}
