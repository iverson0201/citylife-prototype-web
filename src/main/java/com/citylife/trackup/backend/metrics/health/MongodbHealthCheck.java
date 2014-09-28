package com.citylife.trackup.backend.metrics.health;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.codahale.metrics.health.HealthCheck;
import com.mongodb.Mongo;
import com.mongodb.MongoException;

/**
 * @author 作者 E-mail:xujw0201@gmail.com
 * @version 创建时间：2014年9月18日 下午5:23:49
 * 
 */
@Component
public class MongodbHealthCheck extends HealthCheck{

    @Resource(name = "mongo")
    private Mongo mongo;

    @Override
    protected Result check() throws Exception {
        try {
            mongo.getDatabaseNames();
            return HealthCheck.Result.healthy();
        } catch (MongoException e){
            return HealthCheck.Result.unhealthy("Cannot connect to " + mongo.getAddress());
        }
    }
}
