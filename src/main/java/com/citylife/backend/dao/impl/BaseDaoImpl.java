package com.citylife.backend.dao.impl;


import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.citylife.backend.dao.BaseDao;
import com.citylife.backend.exception.NotFoundException;
import com.citylife.backend.exception.OperateFailedException;
import com.codahale.metrics.annotation.ExceptionMetered;

/**
 * 使用Mybatis实现泛型DAO接口
 *
 * @param <T>
 *            实体类型
 * @param <ID>
 *            实体ID的类型
 */
public class BaseDaoImpl<T, ID extends Serializable> implements
        BaseDao<T, ID> {
    private Class<T> persistentClass;

    @Value("#{configs['list.max']}")
    private Integer listmax;

    @SuppressWarnings("unchecked")
	public BaseDaoImpl() {
        persistentClass = (Class<T>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];
    }

    @Resource(name = "mongoTemplate")
    private MongoTemplate mongoTemplate;

    @Override
    public void insert2Mongo(T entity) {
        mongoTemplate.insert(entity);
    }

    @Override
    public void save2Mongo(T entity) {
        mongoTemplate.save(entity);
    }

    @Override
    public void delete2Mongo(T entity) {
        mongoTemplate.remove(entity);

    }

    @ExceptionMetered
    @Override
    public void delete2Mongo(ID id) {
        mongoTemplate.remove(query(where("_id").is(id)), getPersistentClass());
        T entity = mongoTemplate.findById(id, getPersistentClass());
        if(entity != null) {
            throw new OperateFailedException("Delete operation failed.");
        }
    }

    @ExceptionMetered
    @Override
    public long count(Query query) {
        return mongoTemplate.count(query, getPersistentClass());
    }

    @Override
    public T update2Mongo(ID id, T entity) {
//        mongoTemplate.save(entity);
        Update update = new Update();

        Field[] fields = entity.getClass().getDeclaredFields();
        filteUpdateFields(fields, entity, update);
        update.set("updated_at", new Date());
        mongoTemplate.updateFirst(query(where("_id").is(id)), update, getPersistentClass());
        return mongoTemplate.findById(id, getPersistentClass());
    }

    @ExceptionMetered
    @Override
    public T findByIdFromMongo(ID id) {
        T t =mongoTemplate.findById(id, getPersistentClass());
        if (t == null)
            throw new NotFoundException("Id不存在");
        return t;
    }

    @Override
    public List<T> findAllFromMongo(int page, int size, String[] fields, List<Sort.Order> order) {
        page = page <= 0 ? 1 : page;
        size = size <= 0 ? listmax : size;
        Query query = new Query();
        for (String field : fields) {
            if (StringUtils.isNotBlank(field))
                query.fields().include(field);
        }
        return mongoTemplate.find(query
                .skip((page - 1) * size)
                .limit(size)
                .with(new Sort(order)), getPersistentClass());
    }

    @Override
    public List<T> findAllFromMongo(int page, int size, String[] fields, Query query, List<Sort.Order> order) {
        page = page <= 0 ? 1 : page;
        size = size <= 0 ? listmax : size;
        for (String field : fields) {
            if (StringUtils.isNotBlank(field))
                query.fields().include(field);
        }
        return mongoTemplate.find(query.skip((page - 1) * size).limit(size).with(new Sort(order)), getPersistentClass());
    }

    @Override
    public List<T> findAllFromMongo() {
    	Query query = new Query();
    	query.with(new Sort(Sort.Direction.DESC, "updated_at"));
        return mongoTemplate.find(query, getPersistentClass());
    }

    // -- Getter && Setter
    private Class<T> getPersistentClass() {
        return persistentClass;
    }

    @SuppressWarnings("unused")
	private void setPersistentClass(Class<T> persistentClass) {
        this.persistentClass = persistentClass;
    }

    @SuppressWarnings({ "rawtypes", "unused" })
	private void filteUpdateFields(Field[] fields, T entity, Update update) {
        for (Field field : fields) {
            field.setAccessible(true);
            String mod = Modifier.toString(field.getModifiers());

            // 跳过静态属性
            if (mod.indexOf("static") != -1) {
                continue;
            }
            Class type = field.getType();
            try {
                Object value = field.get(entity);
                if (value == null) {
                    continue;
                }
//                if (type.equals(int.class) && Integer.parseInt(value.toString()) == -1) {
//                    continue;
//                }
//                if (type.equals(boolean.class) && !Boolean.parseBoolean(value.toString())) {
//                   continue;
//                }
                if (field.getName().equals("id")) {
                    continue;
                }
                update.set(field.getName(), value);
            } catch (IllegalAccessException e) {

            }
        }
    }
}
