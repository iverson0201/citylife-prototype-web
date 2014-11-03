package com.citylife.backend.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;

import com.citylife.backend.dao.EnshrineDao;
import com.citylife.backend.domain.user.Enshrine;
import com.citylife.backend.service.EnshrineService;

/**
 * @author 作者 E-mail:xujw0201@gmail.com
 * @version 创建时间：2014年10月16日 上午11:55:45
 */
@Service
public class EnshrineServiceImpl implements EnshrineService {

	@Autowired
	private EnshrineDao enshrineDao;

	@Override
	public void create(Enshrine enshrine) {
		// TODO Auto-generated method stub
		enshrineDao.insert2Mongo(enshrine);
	}

	@Override
	public void delete(String enshrineId) {
		// TODO Auto-generated method stub
		enshrineDao.delete2Mongo(enshrineId);
	}

	@Override
	public List<Enshrine> getEnshrines(Integer size, Integer page, String sort,
			String order) {
		// TODO Auto-generated method stub
		String[] fields = {};
		List<Sort.Order> orders = new ArrayList<Sort.Order>();
		Sort.Direction direction = Sort.Direction.fromString(order);
		Order o1 = new Order(direction,sort);
		orders.add(o1);
		return enshrineDao.findAllFromMongo(page, size, fields, orders);
	}

	@Override
	public Enshrine findEnshrine(String id, int status) {
		// TODO Auto-generated method stub
		return enshrineDao.findEnshrine(id,status);
	}

	@Override
	public Enshrine findEnshrine(String id) {
		// TODO Auto-generated method stub
		return enshrineDao.findByIdFromMongo(id);
	}

	@Override
	public Enshrine findEnshrineByPoiId(String id) {
		// TODO Auto-generated method stub
		return enshrineDao.findByPoiId(id);
	}

	@Override
	public Enshrine findEnshrineByParam(String poiId, String userId) {
		// TODO Auto-generated method stub
		return enshrineDao.findEnshrineByParam(poiId,userId);
	}

	@Override
	public void update(Enshrine enshrineRet) {
		// TODO Auto-generated method stub
		enshrineDao.update2Mongo(enshrineRet.getId(), enshrineRet);
	}

	@Override
	public List<Enshrine> getEnshrinesPage(String userId, Integer size,
			Integer page, String sort, String order) {
		// TODO Auto-generated method stub
		return enshrineDao.getPage(userId,size,page,sort,order);
	}
}
