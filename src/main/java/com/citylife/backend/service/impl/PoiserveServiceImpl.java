package com.citylife.backend.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.citylife.backend.dao.PoiserveDao;
import com.citylife.backend.domain.poi.PoiServe;
import com.citylife.backend.service.PoiserveService;

/**
 * @author 作者 E-mail:xujw0201@gmail.com
 * @version 创建时间：2014年12月2日 下午4:59:06
 */
@Service
public class PoiserveServiceImpl implements PoiserveService {

	@Autowired
	private PoiserveDao poiserveDao;

	@Override
	public void create(PoiServe poiServe) {
		// TODO Auto-generated method stub
		poiserveDao.insert2Mongo(poiServe);
	}

	@Override
	public List<PoiServe> all() {
		// TODO Auto-generated method stub
		return poiserveDao.findAllFromMongo();
	}
}
