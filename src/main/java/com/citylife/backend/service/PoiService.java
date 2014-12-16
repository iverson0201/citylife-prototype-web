package com.citylife.backend.service;

import com.citylife.backend.domain.poi.Poi;
import com.citylife.backend.domain.poi.PoiInfo;

/**
 * @author 作者 E-mail:xujw0201@gmail.com
 * @version 创建时间：2014年10月15日 下午1:43:44
 */
public interface PoiService {

	void createPoi(Poi poi);

	Poi getPoi(String poiId);

	void insertPoiInfo(PoiInfo poiInfo);

	boolean findPoiInfoByPoiId(String poiId);

	PoiInfo getPoiInfo(String poiId);

}
