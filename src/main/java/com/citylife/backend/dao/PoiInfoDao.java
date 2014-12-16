package com.citylife.backend.dao;

import com.citylife.backend.domain.poi.PoiInfo;

/**
 * @author 作者 E-mail:xujw0201@gmail.com
 * @version 创建时间：2014年12月3日 下午5:34:50
 */
public interface PoiInfoDao extends BaseDao<PoiInfo, String>{

	boolean existPoiInfo(String poiId);

	PoiInfo findByPoiId(String poiId);

}
