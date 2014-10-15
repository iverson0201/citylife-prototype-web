package com.citylife.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.citylife.backend.common.web.MediaTypes;
import com.citylife.backend.domain.business.catering.Catering;
import com.citylife.backend.service.BusinessService;

/**
 * @author 作者 E-mail:xujw0201@gmail.com
 * @version 创建时间：2014年10月15日 下午1:37:44
 * 商家
 */
public class BusinessController {

	@Autowired
	private BusinessService businessService;
	
	@RequestMapping(value = "/create/catering",method = RequestMethod.POST,produces = MediaTypes.JSON)
	public Catering createCatering(@RequestBody Catering catering){
		businessService.createCatering(catering);
		return catering;
	}
}
