package com.citylife.backend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.citylife.backend.dao.BeautyDao;
import com.citylife.backend.dao.CateringDao;
import com.citylife.backend.dao.ChildrenDao;
import com.citylife.backend.dao.CultureSportDao;
import com.citylife.backend.dao.RecreationDao;
import com.citylife.backend.dao.ShoppingDao;
import com.citylife.backend.dao.impl.ChildrenDaoImpl;
import com.citylife.backend.dao.impl.CultureSportDaoImpl;
import com.citylife.backend.domain.business.catering.Catering;
import com.citylife.backend.domain.business.children.Children;
import com.citylife.backend.domain.business.cultureSport.CultureSport;
import com.citylife.backend.service.BusinessService;

/**
 * @author 作者 E-mail:xujw0201@gmail.com
 * @version 创建时间：2014年10月15日 下午1:44:05
 */
@Service
public class BusinessServiceImpl implements BusinessService {

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

}
