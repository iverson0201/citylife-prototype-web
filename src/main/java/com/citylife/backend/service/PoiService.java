package com.citylife.backend.service;

import com.citylife.backend.domain.poi.Poi;
import com.citylife.backend.domain.poi.beanty.Beauty;
import com.citylife.backend.domain.poi.catering.Catering;

/**
 * @author 作者 E-mail:xujw0201@gmail.com
 * @version 创建时间：2014年10月15日 下午1:43:44
 */
public interface PoiService {

	void createCatering(Catering catering);

	Beauty getBeanty(String poiId);

	Catering getCatering(String poiId);

	void createPoi(Poi poi);

	Poi getPoi(String poiId);

}
