package com.citylife.trackup.backend.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.citylife.trackup.backend.Express2QL;
import com.citylife.trackup.backend.dao.UserDao;
import com.citylife.trackup.backend.domain.user.User;
import com.citylife.trackup.backend.service.UserService;

import cz.jirutka.rsql.parser.model.Expression;


@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public List<User> findAll(int page, int size, String[] fields, Expression expression, List<Sort.Order> order) {
        return userDao.findAllFromMongo(page, size, fields,order);
    }

    @Override
    public List<User> findAll(int page, int size, String[] fields, List<Sort.Order> order) {
        return userDao.findAllFromMongo(page, size, fields,order);
    }

    @Override
    public List<User> findAll() {
        return userDao.findAllFromMongo();
    }

    @Override
    public void insert(User user) {
        userDao.insert2Mongo(user);
    }

    @Override
    public void save(User user) {
        userDao.save2Mongo(user);
    }

    @Override
    public User update(String userid, User user) {
        return userDao.update2Mongo(userid, user);
    }

    @Override
    public void delete(User user) {
        userDao.delete2Mongo(user);
    }

    @Override
    public void delete(String id) {
        userDao.delete2Mongo(id);
    }

    @Override
    public long count(Query query) {
        return userDao.count(query);
    }

    @Override
    public User login(User user) {
        return userDao.login(user);
    }
    @Override
    public User findOne(String id) {
        return userDao.findByIdFromMongo(id);
    }

    @Override
    public User findByUserName(String name) {
        return userDao.findByUserNameFromMongo(name);
    }

    @Override
    public User findByPhoneNum(String num) {
        return userDao.findByPhoneNumFromMongo(num);
    }

    @Override
    public User findByPhoneNumAndPwd(String num, String password) {
        return userDao.findByPhoneNumAndPwdFromMongo(num, password);
    }

	@Override
	public List<User> findAllByFields(Expression expression) {
		if(expression == null){
			return userDao.findAllFromMongo();
		}
		Query query = Express2QL.getQL(expression);
		return userDao.findAllByFields(query);
	}

}
