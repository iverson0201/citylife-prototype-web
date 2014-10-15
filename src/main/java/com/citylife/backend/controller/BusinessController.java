package com.citylife.backend.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.citylife.backend.common.web.MediaTypes;
import com.citylife.backend.domain.business.BusinessReply;
import com.citylife.backend.domain.business.catering.Catering;
import com.citylife.backend.service.BusinessReplyService;
import com.citylife.backend.service.BusinessService;

/**
 * @author 作者 E-mail:xujw0201@gmail.com
 * @version 创建时间：2014年10月15日 下午1:37:44
 * 商家
 */
@RestController
@RequestMapping(value = "/api/v1/business")
public class BusinessController {

	@Autowired
	private BusinessService businessService;
	@Autowired
	private BusinessReplyService businessReplyService;
	/**
	 * 创建餐饮信息
	 * @param catering
	 * @return
	 */
	@RequestMapping(value = "/catering",method = RequestMethod.POST,produces = MediaTypes.JSON)
	public Catering createCatering(@RequestBody Catering catering){
		Date date = new Date();
		catering.setCreatedAt(date);
		catering.setUpdatedAt(date);
		businessService.createCatering(catering);
		return catering;
	}
	/**
	 * 商家评论
	 * @param businessReply
	 * @return
	 */
	@RequestMapping(value = "/reply",method = RequestMethod.POST,produces = MediaTypes.JSON)
	public BusinessReply reply(@RequestBody BusinessReply businessReply){
		Date date = new Date();
		businessReply.setCreatedAt(date);
		businessReply.setUpdatedAt(date);
		businessReplyService.insert(businessReply);
		return businessReply;
	}
	/**
	 * 跟帖
	 * @param businessReply
	 * @return
	 */
	@RequestMapping(value = "/reply/follow",method = RequestMethod.POST,produces = MediaTypes.JSON)
	public BusinessReply follow(@RequestBody BusinessReply businessReply){
		Date date = new Date();
		businessReply.setCreatedAt(date);
		businessReply.setUpdatedAt(date);
		businessReplyService.insert(businessReply);
		return businessReply;
	}
	
}
