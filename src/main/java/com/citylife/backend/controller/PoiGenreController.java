package com.citylife.backend.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.citylife.backend.common.Constant;
import com.citylife.backend.common.web.MediaTypes;
import com.citylife.backend.domain.poi.PoiGenre;
import com.citylife.backend.exception.RestException;
import com.citylife.backend.service.PoiGenreService;
import com.citylife.backend.service.impl.poiGenreServiceImpl;

/**
 * @author 作者 E-mail:xujw0201@gmail.com
 * @version 创建时间：2014年12月2日 下午5:04:42
 * poi分类管理
 */
@RestController
@RequestMapping(value = "/api/v1/poiGenre")
public class PoiGenreController {

	@Autowired
	private PoiGenreService poiGenreService = new poiGenreServiceImpl();

	/**
	 * 创建poi一级分类
	 * @param poiGenre
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST,consumes = MediaTypes.JSON)
	public PoiGenre createParent(@RequestBody PoiGenre poiGenre){
		boolean flag = poiGenreService.findByCode(poiGenre.getCode());
		if(flag)
			throw new RestException(Constant.CATEGORY_EXIST);
		Date date = new Date();
		poiGenre.setCreatedAt(date);
		poiGenre.setUpdatedAt(date);
		poiGenreService.create(poiGenre);
		return poiGenre;
	}
	
	/**
	 * 创建poi子级分类
	 * @param parentCode
	 * @param poiGenre
	 * @return
	 */
	@RequestMapping(value = "/parent/{parentCode}",method = RequestMethod.POST,consumes = MediaTypes.JSON)
	public PoiGenre createSubset(@PathVariable String parentCode, @RequestBody PoiGenre poiGenre){
		PoiGenre parentPoiGenre = poiGenreService.findPoiGenreByCode(parentCode);
		if(parentPoiGenre == null)
			throw new RestException(Constant.PARENT_CATEGORY_NOT_EXIST);
		poiGenre.setParentPoiGenre(parentPoiGenre);
		boolean flag = poiGenreService.findByCode(poiGenre.getCode());
		if(flag)
			throw new RestException(Constant.SON_CATEGORY_EXIST);
		Date date = new Date();
		poiGenre.setCreatedAt(date);
		poiGenre.setUpdatedAt(date);
		poiGenreService.create(poiGenre);
		return poiGenre;
	}
	/**
	 * 得到一条poi分类信息
	 * @param code
	 * @return
	 */
	@RequestMapping(value = "/{code}",method = RequestMethod.GET,produces = MediaTypes.JSON_UTF_8)
	public PoiGenre get(@PathVariable String code){
		return poiGenreService.findPoiGenreByCode(code);
	}
}
