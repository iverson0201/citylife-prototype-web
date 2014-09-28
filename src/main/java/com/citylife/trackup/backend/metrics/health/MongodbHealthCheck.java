package com.citylife.trackup.backend.metrics.health;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.codahale.metrics.health.HealthCheck;
import com.mongodb.Mongo;
import com.mongodb.MongoException;

/**

 * User: Administrator
 * Date: 13-6-20
 * Time: 下午1:47
 * To change this template use File | Settings | File Templates.
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
