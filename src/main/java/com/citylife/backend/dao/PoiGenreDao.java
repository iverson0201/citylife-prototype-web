package com.citylife.backend.dao;

import com.citylife.backend.domain.poi.PoiGenre;

/**
 * @author 作者 E-mail:xujw0201@gmail.com
 * @version 创建时间：2014年12月2日 下午5:07:53
 */
public interface PoiGenreDao extends BaseDao<PoiGenre, String>{

	boolean existsPoiGenreByCode(String code);

	PoiGenre findPoiGenreByCode(String parentCode);

}
