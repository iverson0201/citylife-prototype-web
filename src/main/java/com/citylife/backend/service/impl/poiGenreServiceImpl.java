package com.citylife.backend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.citylife.backend.dao.PoiGenreDao;
import com.citylife.backend.domain.poi.PoiGenre;
import com.citylife.backend.service.PoiGenreService;

/**
 * @author 作者 E-mail:xujw0201@gmail.com
 * @version 创建时间：2014年12月2日 下午5:06:05
 */
@Service
public class poiGenreServiceImpl implements PoiGenreService {

	@Autowired
	private PoiGenreDao poiGenreDao;
	@Override
	public void create(PoiGenre poiGenre) {
		// TODO Auto-generated method stub
		poiGenreDao.insert2Mongo(poiGenre);
	}
	@Override
	public boolean findByCode(String code) {
		// TODO Auto-generated method stub
		return poiGenreDao.existsPoiGenreByCode(code);
	}
	@Override
	public PoiGenre findPoiGenreByCode(String parentCode) {
		// TODO Auto-generated method stub
		return poiGenreDao.findPoiGenreByCode(parentCode);
	}

}
