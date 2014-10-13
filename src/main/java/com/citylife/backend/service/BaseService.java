package com.citylife.backend.service;


import cz.jirutka.rsql.parser.model.Expression;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

/**
 * @author 作者 E-mail:xujw0201@gmail.com
 * @version 创建时间：2014年9月19日 下午3:22:11
 * 
 */
public interface BaseService<T> {

    List<T> findAll(int page, int size, String[] fields, Expression expression, List<Sort.Order> order);

    List<T> findAll(int page, int size, String[] fields, List<Sort.Order> order);

    List<T> findAll();

    T findOne(String id);

    void insert(T t);

    void save(T t);

    T update(String id, T t);

    void delete(T t);

    void delete(String id);

    long count(Query query);
}
