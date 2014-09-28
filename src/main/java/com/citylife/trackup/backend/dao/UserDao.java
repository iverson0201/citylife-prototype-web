package com.citylife.trackup.backend.dao;

import java.util.List;

import org.springframework.data.mongodb.core.query.Query;

import com.citylife.trackup.backend.domain.user.User;

/**
 * @author 作者 E-mail:xujw0201@gmail.com
 * @version 创建时间：2014年9月19日 下午5:36:55
 * 
 */
public interface UserDao extends BaseDao<User, String> {
    User findByUserNameFromMongo(String name);

    User findByPhoneNumFromMongo(String num);

    User findByPhoneNumAndPwdFromMongo(String num, String password);

    User login(User user);
    
    List<User> findAllByFields(Query query);

    User testSQL(String name);

}
