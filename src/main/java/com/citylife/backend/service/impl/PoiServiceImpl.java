package com.citylife.backend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.citylife.backend.dao.BeautyDao;
import com.citylife.backend.dao.CateringDao;
import com.citylife.backend.dao.ChildrenDao;
import com.citylife.backend.dao.CultureSportDao;
import com.citylife.backend.dao.PoiDao;
import com.citylife.backend.dao.RecreationDao;
import com.citylife.backend.dao.ShoppingDao;
import com.citylife.backend.domain.poi.Poi;
import com.citylife.backend.domain.poi.beanty.Beauty;
import com.citylife.backend.domain.poi.catering.Catering;
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
	private CateringDao cateringDao;
	@Autowired
	private RecreationDao recreationDao;
	@Autowired
	private ShoppingDao shoppingDao;
	@Autowired
	private BeautyDao beautyDao;
	@Autowired
	private CultureSportDao cultureSportDao;
	@Autowired
	private ChildrenDao childrenDao;
	
	
	@Override
	public void createCatering(Catering catering) {
		// TODO Auto-generated method stub
		cateringDao.insert2Mongo(catering);
	}


	@Override
	public Beauty getBeanty(String poiId) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Catering getCatering(String poiId) {
		// TODO Auto-generated method stub
		return cateringDao.findByPoiId(poiId);
	}


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

}
