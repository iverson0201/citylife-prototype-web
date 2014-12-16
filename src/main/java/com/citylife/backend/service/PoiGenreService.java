package com.citylife.backend.service;

import com.citylife.backend.domain.poi.PoiGenre;

/**
 * @author 作者 E-mail:xujw0201@gmail.com
 * @version 创建时间：2014年12月2日 下午5:05:40
 */
public interface PoiGenreService {

	void create(PoiGenre poiGenre);

	boolean findByCode(String code);

	PoiGenre findPoiGenreByCode(String parentCode);

}
