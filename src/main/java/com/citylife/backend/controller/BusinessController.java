package com.citylife.backend.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.citylife.backend.common.Utils;
import com.citylife.backend.common.mapper.BeanMapper;
import com.citylife.backend.common.web.MediaTypes;
import com.citylife.backend.domain.business.BusinessComment;
import com.citylife.backend.domain.business.BusinessReply;
import com.citylife.backend.domain.business.catering.Catering;
import com.citylife.backend.domain.person.Backer;
import com.citylife.backend.domain.result.Result;
import com.citylife.backend.domain.result.Results;
import com.citylife.backend.dto.BusinessCommentDto;
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
	@RequestMapping(value = "/catering",method = RequestMethod.POST,consumes = MediaTypes.JSON)
	public Catering createCatering(@RequestBody Catering catering){
		Date date = new Date();
		catering.setCreatedAt(date);
		catering.setUpdatedAt(date);
		businessService.createCatering(catering);
		return catering;
	}
	/**
	 * 商家评论，点评
	 * @param businessComment
	 * @return
	 */
	@RequestMapping(value = "/comment/{businessId}",method = RequestMethod.POST,consumes = MediaTypes.JSON)
	public Result<BusinessComment> comment(@RequestBody BusinessComment businessComment,@PathVariable String businessId,BindingResult bindingResult){
		if(bindingResult.hasErrors()){
			throw new IllegalArgumentException(Utils.parseErrors(bindingResult.getFieldErrors()));
		}
		Date date = new Date();
		businessComment.setCreatedAt(date);
		businessComment.setUpdatedAt(date);
		businessComment.setBusinessId(businessId);
		businessReplyService.createComment(businessComment);
		Result<BusinessComment> result = new Result<BusinessComment>();
		result.setObj(businessComment);
		return result;
	}
	/**
	 * 商家详情 （商家首页）
	 * @param poiId
	 * @param type
	 * @return
	 */
	@RequestMapping(value = "/{type}/{poiId}",method = RequestMethod.GET,produces = MediaTypes.JSON_UTF_8)
	public Result<Object> get(@PathVariable String poiId,@PathVariable int type){
		Object obj = getBusiness(type,poiId);
		Result<Object> result = new Result<Object>();
		result.setObj(obj);
		return result;
	}
	
	private Object getBusiness(int type, String poiId) {
		// TODO Auto-generated method stub
		if(type == 1){
			return businessService.getBeanty(poiId);
		}else if(type == 2){
			return businessService.getCatering(poiId);
		}
		return null;
	}
	/**
	 * 回复评论
	 * @param businessReply
	 * @return
	 */
	@RequestMapping(value = "/reply/{commentId}",method = RequestMethod.POST,consumes = MediaTypes.JSON)
	public Result<BusinessReply> reply(@RequestBody BusinessReply businessReply,@PathVariable String commentId,BindingResult bindingResult){
		if(bindingResult.hasErrors()){
			throw new IllegalArgumentException(Utils.parseErrors(bindingResult.getFieldErrors()));
		}
		Date date = new Date();
		businessReply.setCreatedAt(date);
		businessReply.setUpdatedAt(date);
		businessReply.setCommentId(commentId);
		businessReplyService.insert(businessReply);
		BusinessComment businessComment = businessReplyService.getComment(businessReply.getCommentId());
		businessReplyService.updateComment(businessReply.getCommentId(),businessComment.getReplyCount() + 1);
		Result<BusinessReply> result = new Result<BusinessReply>();
		result.setObj(businessReply);
		return result;
	}
	/**
	 * 跟帖
	 * @param businessReply
	 * @return
	 */
	@RequestMapping(value = "/follow/{commentId}",method = RequestMethod.POST,consumes = MediaTypes.JSON)
	public Result<BusinessReply> follow(@RequestBody BusinessReply businessReply,@PathVariable String commentId
			,@RequestParam String userId,@RequestParam String userName,@RequestParam String headImage,BindingResult bindingResult){
		if(bindingResult.hasErrors()){
			throw new IllegalArgumentException(Utils.parseErrors(bindingResult.getFieldErrors()));
		}
		Date date = new Date();
		businessReply.setCreatedAt(date);
		businessReply.setUpdatedAt(date);
		businessReply.setCommentId(commentId);
		Backer backer = new Backer(userId,userName,headImage);
		businessReply.setBacker(backer);
		businessReplyService.insert(businessReply);
		BusinessComment businessComment = businessReplyService.getComment(businessReply.getCommentId());
		businessReplyService.updateComment(businessReply.getCommentId(),businessComment.getReplyCount() + 1);
		Result<BusinessReply> result = new Result<BusinessReply>();
		result.setObj(businessReply);
		return result;
	}
	/**
	 * 某个商家的所有评论列表
	 * @param businessId
	 * @param size
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "/comment/list/{businessId}",method = RequestMethod.GET,produces = MediaTypes.JSON_UTF_8)
	public Results<BusinessCommentDto> commentList(@PathVariable String businessId,
			@RequestParam(value = "size",required = false,defaultValue = "5") int size,
			@RequestParam(value = "page",required = false,defaultValue = "1") int page){
		List<BusinessComment> businessComments = businessReplyService.getCommentList(businessId,size,page);
		Results<BusinessCommentDto> results = new Results<BusinessCommentDto>();
		List<BusinessCommentDto> businessCommentDtos = BeanMapper.mapList(businessComments, BusinessCommentDto.class);
		results.setList(businessCommentDtos);
		return results;
	}
	/**
	 * 某个用户对商家的所有评论
	 * @param userId
	 * @param size
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "/user/comment/list/{userId}",method = RequestMethod.GET,produces = MediaTypes.JSON_UTF_8)
	public Results<BusinessComment> userCommentList(@PathVariable String userId,
	@RequestParam(value = "size",required = false,defaultValue = "5") int size,
	@RequestParam(value = "page",required = false,defaultValue = "1") int page){
		List<BusinessComment> businessComments = businessReplyService.getUserCommentList(userId,size,page);
		Results<BusinessComment> results = new Results<BusinessComment>();
		results.setList(businessComments);
		return results;
	}
	/**
	 * 回复列表
	 * @param commentId
	 * @param size
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "/reply/list/{commentId}",method = RequestMethod.GET,produces = MediaTypes.JSON_UTF_8)
	public Results<BusinessReply> replyList(@PathVariable String commentId,
			@RequestParam(value = "size",required = false,defaultValue = "5") int size,
			@RequestParam(value = "page",required = false,defaultValue = "1") int page){
		List<BusinessReply> businessReplies = businessReplyService.getReplyList(commentId,size,page);
		Results<BusinessReply> results = new Results<BusinessReply>();
		results.setList(businessReplies);
		return results;
	}
}
