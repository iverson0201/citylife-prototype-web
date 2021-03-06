package com.citylife.backend.service;

import java.util.List;

import com.citylife.backend.domain.user.User;

import cz.jirutka.rsql.parser.model.Expression;


public interface UserService extends BaseService<User> {

    User findByUserName(String name);

    User findByPhoneNum(String num);

    User findByPhoneNumAndPwd(String num, String password);

    User login(User user);

    List<User> findAllByFields(Expression expression);

	User findByThirdUser(Integer type, String thirdId);

	User findUserByFollowId(String followId);
    
}
