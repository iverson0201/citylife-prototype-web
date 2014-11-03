package com.citylife.backend.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.citylife.backend.common.web.MediaTypes;
import com.citylife.backend.domain.result.Results;
import com.citylife.backend.domain.user.Enshrine;
import com.citylife.backend.service.EnshrineService;

/**
 * @author 作者 E-mail:xujw0201@gmail.com
 * @version 创建时间：2014年10月16日 上午11:54:33
 * 收藏接口
 */
@RestController
@RequestMapping(value = "/api/v1/enshrine")
public class EnshrineController {

	@Autowired
	private EnshrineService enshrineService;
	/**
	 * 收藏 | 取消收藏
	 * @param enshrine
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST,consumes = MediaTypes.JSON)
	public Enshrine enshrine(@RequestBody Enshrine enshrine){
		Enshrine enshrineRet = enshrineService.findEnshrineByParam(enshrine.getPoiId(),enshrine.getUserId());
		if(enshrineRet == null){
			Date date = new Date();
			enshrine.setCreatedAt(date);
			enshrine.setUpdatedAt(date);
			enshrine.setStatus(1);
			enshrineService.create(enshrine);
			return enshrine;
		}else{
			if(enshrineRet.getStatus() == 0){
				enshrineRet.setStatus(1);
			}else{
				enshrineRet.setStatus(0);
			}
			enshrineService.update(enshrineRet);
			return enshrineRet;
		}
	}
	/**
	 * 用户收藏列表
	 * @param userId
	 * @param size
	 * @param page
	 * @param sort
	 * @param order
	 * @return
	 */
	@RequestMapping(value = "/list/{userId}",method = RequestMethod.GET,produces = MediaTypes.JSON_UTF_8)
	public Results<Enshrine> list(@PathVariable String userId,
	@RequestParam(value = "size",required = false,defaultValue = "10") Integer size,
	@RequestParam(value = "page",required = false,defaultValue = "1") Integer page,
	@RequestParam(value = "sort",required = false,defaultValue = "updateAt") String sort,
	@RequestParam(value = "order",required = false,defaultValue = "DESC") String order 
			){
		Results<Enshrine> results = new Results<Enshrine>();
		List<Enshrine> list = enshrineService.getEnshrinesPage(userId,size,page,sort,order);
		results.setList(list);
		return results;
	}
}
