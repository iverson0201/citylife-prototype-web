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

import com.citylife.backend.common.Constant;
import com.citylife.backend.common.Utils;
import com.citylife.backend.common.mapper.BeanMapper;
import com.citylife.backend.common.web.MediaTypes;
import com.citylife.backend.domain.person.Backer;
import com.citylife.backend.domain.poi.Poi;
import com.citylife.backend.domain.poi.PoiComment;
import com.citylife.backend.domain.poi.PoiInfo;
import com.citylife.backend.domain.poi.PoiReply;
import com.citylife.backend.domain.result.Result;
import com.citylife.backend.domain.result.Results;
import com.citylife.backend.dto.PoiCommentDto;
import com.citylife.backend.dto.PoiInfoDto;
import com.citylife.backend.exception.RestException;
import com.citylife.backend.service.PoiReplyService;
import com.citylife.backend.service.PoiService;

/**
 * @author 作者 E-mail:xujw0201@gmail.com
 * @version 创建时间：2014年10月15日 下午1:37:44
 * 商家
 */
@RestController
@RequestMapping(value = "/api/v1/poi")
public class PoiController {

	@Autowired
	private PoiService poiService;
	@Autowired
	private PoiReplyService poiReplyService;
	
	/**
	 * 创建PoiInfo信息
	 * @param catering
	 * @return
	 */
	@RequestMapping(value = "/insert",method = RequestMethod.POST,consumes = MediaTypes.JSON)
	public PoiInfo insert(@RequestBody PoiInfo poiInfo){
		boolean flag = poiService.findPoiInfoByPoiId(poiInfo.getPoiId());
		if(flag)
			throw new RestException(Constant.POI_EXIST);
		Date date = new Date();
		poiInfo.setCreatedAt(date);
		poiInfo.setUpdatedAt(date);
		poiService.insertPoiInfo(poiInfo);
		return poiInfo;
	}
	
	/**
	 * 创建POI信息
	 * @param catering
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST,consumes = MediaTypes.JSON)
	public Poi createPoi(@RequestBody Poi poi){
		Date date = new Date();
		poi.setCreatedAt(date);
		poi.setUpdatedAt(date);
		poiService.createPoi(poi);
		return poi;
	}
	

	/**
	 * 商家评论，点评
	 * @param poiComment
	 * @return
	 */
	@RequestMapping(value = "/comment/{poiId}",method = RequestMethod.POST,consumes = MediaTypes.JSON)
	public Result<PoiComment> comment(@RequestBody PoiComment poiComment,@PathVariable String poiId,BindingResult bindingResult){
		if(bindingResult.hasErrors()){
			throw new IllegalArgumentException(Utils.parseErrors(bindingResult.getFieldErrors()));
		}
		Date date = new Date();
		poiComment.setCreatedAt(date);
		poiComment.setUpdatedAt(date);
		poiComment.setPoiId(poiId);
		poiReplyService.createComment(poiComment);
		Result<PoiComment> result = new Result<PoiComment>();
		result.setObj(poiComment);
		return result;
	}
	/**
	 * 商家详情 （商家首页）
	 * 根据poiId查找商家信息
	 * @param poiId
	 * @param type
	 * @return
	 */
	@RequestMapping(value = "/detail/{poiId}",method = RequestMethod.GET,produces = MediaTypes.JSON_UTF_8)
	public Result<PoiInfoDto> get(@PathVariable String poiId){
		Poi poi = poiService.getPoi(poiId);
		PoiInfo poiInfo = poiService.getPoiInfo(poiId);
		PoiInfoDto poiInfoDto = new PoiInfoDto(poi, poiInfo);
		Result<PoiInfoDto> result = new Result<PoiInfoDto>();
		result.setObj(poiInfoDto);
		return result;
	}

	/**
	 * 回复评论
	 * @param poiReply
	 * @return
	 */
	@RequestMapping(value = "/reply/{commentId}",method = RequestMethod.POST,consumes = MediaTypes.JSON)
	public Result<PoiReply> reply(@RequestBody PoiReply poiReply,@PathVariable String commentId,BindingResult bindingResult){
		if(bindingResult.hasErrors()){
			throw new IllegalArgumentException(Utils.parseErrors(bindingResult.getFieldErrors()));
		}
		Date date = new Date();
		poiReply.setCreatedAt(date);
		poiReply.setUpdatedAt(date);
		poiReply.setCommentId(commentId);
		poiReplyService.insert(poiReply);
		PoiComment poiComment = poiReplyService.getComment(poiReply.getCommentId());
		poiReplyService.updateComment(poiReply.getCommentId(),poiComment.getReplyCount() + 1);
		Result<PoiReply> result = new Result<PoiReply>();
		result.setObj(poiReply);
		return result;
	}
	/**
	 * 跟帖
	 * @param poiReply
	 * @return
	 */
	@RequestMapping(value = "/follow/{commentId}",method = RequestMethod.POST,consumes = MediaTypes.JSON)
	public Result<PoiReply> follow(@RequestBody PoiReply poiReply,@PathVariable String commentId
			,@RequestParam String userId,@RequestParam String userName,@RequestParam String headImage,BindingResult bindingResult){
		if(bindingResult.hasErrors()){
			throw new IllegalArgumentException(Utils.parseErrors(bindingResult.getFieldErrors()));
		}
		Date date = new Date();
		poiReply.setCreatedAt(date);
		poiReply.setUpdatedAt(date);
		poiReply.setCommentId(commentId);
		Backer backer = new Backer(userId,userName,headImage);
		poiReply.setBacker(backer);
		poiReplyService.insert(poiReply);
		PoiComment poiComment = poiReplyService.getComment(poiReply.getCommentId());
		poiReplyService.updateComment(poiReply.getCommentId(),poiComment.getReplyCount() + 1);
		Result<PoiReply> result = new Result<PoiReply>();
		result.setObj(poiReply);
		return result;
	}
	/**
	 * 某个商家的所有评论列表
	 * @param poiId
	 * @param size
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "/comment/list/{poiId}",method = RequestMethod.GET,produces = MediaTypes.JSON_UTF_8)
	public Results<PoiCommentDto> commentList(@PathVariable String poiId,
			@RequestParam(value = "size",required = false,defaultValue = "5") int size,
			@RequestParam(value = "page",required = false,defaultValue = "1") int page){
		List<PoiComment> poiComments = poiReplyService.getCommentList(poiId,size,page);
		Results<PoiCommentDto> results = new Results<PoiCommentDto>();
		long total = poiReplyService.getAllCommentCount(poiId);
		List<PoiCommentDto> poiCommentDtos = BeanMapper.mapList(poiComments, PoiCommentDto.class);
		results.setTotal(total);
		results.setList(poiCommentDtos);
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
	public Results<PoiComment> userCommentList(@PathVariable String userId,
	@RequestParam(value = "size",required = false,defaultValue = "5") int size,
	@RequestParam(value = "page",required = false,defaultValue = "1") int page){
		long total = poiReplyService.getAllUserCommentListCount(userId);
		List<PoiComment> poiComments = poiReplyService.getUserCommentList(userId,size,page);
		Results<PoiComment> results = new Results<PoiComment>();
		results.setTotal(total);
		results.setList(poiComments);
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
	public Results<PoiReply> replyList(@PathVariable String commentId,
			@RequestParam(value = "size",required = false,defaultValue = "5") int size,
			@RequestParam(value = "page",required = false,defaultValue = "1") int page){
		long total = poiReplyService.getAllReplyListCount(commentId);
		List<PoiReply> poiReplies = poiReplyService.getReplyList(commentId,size,page);
		Results<PoiReply> results = new Results<PoiReply>();
		results.setTotal(total);
		results.setList(poiReplies);
		return results;
	}
}
