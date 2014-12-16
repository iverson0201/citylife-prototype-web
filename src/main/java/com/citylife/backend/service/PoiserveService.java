package com.citylife.backend.service;

import java.util.List;

import com.citylife.backend.domain.poi.PoiServe;

/**
 * @author 作者 E-mail:xujw0201@gmail.com
 * @version 创建时间：2014年12月2日 下午4:58:49
 */
public interface PoiserveService {

	void create(PoiServe poiServe);

	List<PoiServe> all();

}
