package com.citylife.backend.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.citylife.backend.common.web.MediaTypes;
import com.citylife.backend.domain.poi.PoiServe;
import com.citylife.backend.service.PoiserveService;

/**
 * @author 作者 E-mail:xujw0201@gmail.com
 * @version 创建时间：2014年12月2日 下午4:56:15
 * poi服务管理
 */
@RestController
@RequestMapping(value = "/api/v1/poiServe")
public class PoiServeController {
	
	@Autowired
	private PoiserveService poiserveService;

	/**
	 * 创建poi服务
	 * @param poiServe
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST,consumes = MediaTypes.JSON)
	public PoiServe create(@RequestBody PoiServe poiServe){
		Date date = new Date();
		poiServe.setCreatedAt(date);
		poiServe.setUpdatedAt(date);
		poiserveService.create(poiServe);
		return poiServe;
	}
	/**
	 * 得到所有poi服务
	 * @return
	 */
	@RequestMapping(value = "/all",method = RequestMethod.GET,produces = MediaTypes.JSON_UTF_8)
	public List<PoiServe> all(){
		return poiserveService.all();
	}
	
}
