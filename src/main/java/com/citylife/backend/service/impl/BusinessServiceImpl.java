package com.citylife.backend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.citylife.backend.dao.BeautyDao;
import com.citylife.backend.dao.CateringDao;
import com.citylife.backend.dao.RecreationDao;
import com.citylife.backend.dao.ShoppingDao;
import com.citylife.backend.domain.business.catering.Catering;
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
	
	
	@Override
	public void createCatering(Catering catering) {
		// TODO Auto-generated method stub
		
	}

}
