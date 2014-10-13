package com.citylife.backend.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Query;

/**
 * @author 作者 E-mail:xujw0201@gmail.com
 * @version 创建时间：2014年9月19日 下午5:44:12
 * 
 */
public interface BaseDao<T,PK extends Serializable> {

    public void insert2Mongo(T entity);

    public void save2Mongo(T entity);

    public void delete2Mongo(T entity);

    public void delete2Mongo(PK pk);

    public T update2Mongo(PK pk, T entity);

    public T findByIdFromMongo(PK pk);

    public List<T> findAllFromMongo(int page, int size, String[] fields, List<Sort.Order> order);

    public List<T> findAllFromMongo(int page, int size, String[] fields, Query query, List<Sort.Order> order);

    public List<T> findAllFromMongo();

    public long count(Query query);
	
}
