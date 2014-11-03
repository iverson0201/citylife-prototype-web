package com.citylife.backend.service;

import com.citylife.backend.domain.business.beanty.Beauty;
import com.citylife.backend.domain.business.catering.Catering;

/**
 * @author 作者 E-mail:xujw0201@gmail.com
 * @version 创建时间：2014年10月15日 下午1:43:44
 */
public interface BusinessService {

	void createCatering(Catering catering);

	Beauty getBeanty(String poiId);

	Catering getCatering(String poiId);

}
