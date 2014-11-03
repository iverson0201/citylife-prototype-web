package com.citylife.backend.dao.impl;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.citylife.backend.dao.UserDao;
import com.citylife.backend.domain.user.User;

@Repository
public class UserDaoImpl extends BaseDaoImpl<User, String> implements
        UserDao {
	
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
        return mongoTemplate.findOne(query(where("username").is(name)), User.class);
    }

	@Override
	public User findByThird(Integer type, String thirdId) {
		// TODO Auto-generated method stub
//		return mongoTemplate.findOne(query(where("thirdType").is(type)
//				.and("thirdId").is(thirdId)), User.class);
		return mongoTemplate.findOne(query(where("thirdUsers.thirdType").is(type)
				.and("thirdUsers.thirdId").is(thirdId)), User.class);
	}

	@Override
	public User findByFollowId(String followId) {
		// TODO Auto-generated method stub
		return mongoTemplate.findOne(query(where("follows.followId").is(followId)), User.class);
	}
}
