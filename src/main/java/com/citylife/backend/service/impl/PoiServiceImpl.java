package com.citylife.backend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.citylife.backend.dao.PoiDao;
import com.citylife.backend.dao.PoiInfoDao;
import com.citylife.backend.dao.impl.PoiInfoDaoImpl;
import com.citylife.backend.domain.poi.Poi;
import com.citylife.backend.domain.poi.PoiInfo;
import com.citylife.backend.service.PoiService;

/**
 * @author 作者 E-mail:xujw0201@gmail.com
 * @version 创建时间：2014年10月15日 下午1:44:05
 */
@Service
public class PoiServiceImpl implements PoiService {

	@Autowired
	private PoiDao poiDao;
	@Autowired
	private PoiInfoDao poiInfoDao = new PoiInfoDaoImpl();
	
	@Override
	public void createPoi(Poi poi) {
		// TODO Auto-generated method stub
		poiDao.insert2Mongo(poi);
	}


	@Override
	public Poi getPoi(String poiId) {
		// TODO Auto-generated method stub
		return poiDao.findByIdFromMongo(poiId);
	}


	@Override
	public void insertPoiInfo(PoiInfo poiInfo) {
		// TODO Auto-generated method stub
		poiInfoDao.insert2Mongo(poiInfo);
	}


	@Override
	public boolean findPoiInfoByPoiId(String poiId) {
		// TODO Auto-generated method stub
		return poiInfoDao.existPoiInfo(poiId);
	}


	@Override
	public PoiInfo getPoiInfo(String poiId) {
		// TODO Auto-generated method stub
		return poiInfoDao.findByPoiId(poiId);
	}

}
