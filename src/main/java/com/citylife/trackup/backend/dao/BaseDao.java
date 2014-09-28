package com.citylife.trackup.backend.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Query;

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
