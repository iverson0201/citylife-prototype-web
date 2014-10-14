package com.citylife.backend.controller;

import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.citylife.backend.common.Utils;
import com.citylife.backend.common.mapper.BeanMapper;
import com.citylife.backend.common.web.MediaTypes;
import com.citylife.backend.domain.result.Result;
import com.citylife.backend.domain.topic.Topic;
import com.citylife.backend.domain.topic.TopicReply;
import com.citylife.backend.dto.TopicDto;
import com.citylife.backend.dto.TopicReplyDto;
import com.citylife.backend.exception.RestException;
import com.citylife.backend.service.TopicReplyService;
import com.citylife.backend.service.TopicService;

/**
 * @author 作者 E-mail:xujw0201@gmail.com
 * @version 创建时间：2014年10月11日 下午4:46:44
 */
@RestController
@RequestMapping("/api/v1/topic")
public class TopicController {

	@Autowired
	private TopicService topicService;
	@Autowired
	private TopicReplyService topicReplyService;
	/**
	 * 发布话题
	 * @param topic
	 * @param bindingResult
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST,consumes = MediaTypes.JSON)
	public Result<TopicDto> create(@RequestBody @Valid Topic topic,BindingResult bindingResult){
		if(bindingResult.hasErrors()){
			throw new IllegalArgumentException(Utils.parseErrors(bindingResult.getFieldErrors()));
		}
		Date date = new Date();
		topic.setCreatedAt(date);
		topic.setUpdatedAt(date);
		topicService.insertTopic(topic);
		return extracted(topic);
	}
	/**
	 * 获取某个话题详情
	 * @param topicId
	 * @return
	 */
	@RequestMapping(value = "/{topicId}",method = RequestMethod.GET,produces = MediaTypes.JSON_UTF_8)
	public Result<TopicDto> get(@PathVariable String topicId){
		Topic topic = topicService.getTopic(topicId);
		if(topic == null){
			throw new RestException("话题不存在");
		}
		return extracted(topic);
	}
	/**
	 * 更新话题信息
	 * @param topic
	 * @return
	 */
	@RequestMapping(method = RequestMethod.PUT,produces = MediaTypes.JSON_UTF_8)
	public Result<TopicDto> update(@RequestBody Topic topic){
		Topic topicRet = topicService.updateTopic(topic.getId(),topic);
		return extracted(topicRet);
	}
	/**
	 * 删除话题
	 * @param topicId
	 * @return
	 */
	@RequestMapping(value ="/{topicId}",method = RequestMethod.DELETE,produces = MediaTypes.JSON_UTF_8)
	public String delete(@PathVariable String topicId){
		topicService.deleteTopic(topicId);
		return "{\"code\" : 1}";
	}
	private Result<TopicDto> extracted(Topic topic) {
		TopicDto topicDto = BeanMapper.map(topic, TopicDto.class);
		Result<TopicDto> result = new Result<TopicDto>();
		result.setObj(topicDto);
		return result;
	}
	/**
	 * 回复话题
	 * @param topicReply
	 * @return
	 */
	@RequestMapping(value = "/reply",method = RequestMethod.POST,produces = MediaTypes.JSON)
	public Result<TopicReplyDto> reply(@RequestBody TopicReply topicReply){
		topicReplyService.insert(topicReply);
		return extractedReply(topicReply);
	}
	/**
	 * 删除回复 
	 * @param replyId
	 * @return
	 */
	@RequestMapping(value = "/reply/{replyId}",method = RequestMethod.DELETE,consumes = MediaTypes.JSON_UTF_8)
	public String deleteReply(@PathVariable String replyId){
		topicReplyService.deleteReply(replyId);
		return "{\"code\" : 1}";
	}
	
	/**
	 * 跟帖
	 * @param topicReply
	 * @return
	 */
	@RequestMapping(value = "/reply/follow",method = RequestMethod.POST,produces = MediaTypes.JSON)
	public Result<TopicReplyDto> replyFollow(@RequestBody TopicReply topicReply){
		topicReplyService.insert(topicReply);
		return extractedReply(topicReply);
	}
	
	private Result<TopicReplyDto> extractedReply(TopicReply topicReply) {
		TopicReplyDto topicReplyDto = BeanMapper.map(topicReply, TopicReplyDto.class);
		Result<TopicReplyDto> result = new Result<TopicReplyDto>();
		result.setObj(topicReplyDto);
		return result;
	}
}
