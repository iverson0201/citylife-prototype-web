package com.citylife.backend.controller;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.citylife.backend.common.Utils;
import com.citylife.backend.common.mapper.BeanMapper;
import com.citylife.backend.common.web.MediaTypes;
import com.citylife.backend.domain.result.Result;
import com.citylife.backend.domain.result.Results;
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
 * 话题
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
	@RequestMapping(value = "/reply/"
			+ "{replyId}",method = RequestMethod.DELETE,consumes = MediaTypes.JSON_UTF_8)
	public String deleteReply(@PathVariable String replyId){
		topicReplyService.deleteReply(replyId);
		return "{\"code\" : 1}";
	}
	/**
	 * 话题列表分页
	 * @param size
	 * @param page
	 * @param sort
	 * @param order
	 * @return
	 */
	@RequestMapping(value = "/list",method = RequestMethod.GET,consumes = MediaTypes.JSON_UTF_8)
	public Results<Topic> getTopics(
	@RequestParam(value = "size",required = false,defaultValue = "10") Integer size,
	@RequestParam(value = "page",required = false,defaultValue = "1") int page,
	@RequestParam(value = "sort",required = false,defaultValue = "updateAt") String sort, 
	@RequestParam(value = "order",required = false,defaultValue = "DESC") String order ){
		Assert.isTrue(page > 0, "Page index must be greater than 0");
        Assert.isTrue(size > 0, "Size must be greater than 0");
        Assert.isTrue("DESC".intern() == order.intern() || "ASC".intern() == order.intern(), "The value of order must be 'DESC' or 'ASC'");
		Results<Topic> results = new Results<Topic>();
		List<Topic> topics = topicService.getTopics(size,page,sort,order);
		results.setList(topics);
        return results;
	}
	
	/**
	 * 跟帖
	 * @param topicReply
	 * 
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

	@RequestMapping(value = "/test",method = RequestMethod.GET,consumes = MediaTypes.JSON_UTF_8)
	public String test(@RequestHeader(value = "nihao") String etag){
		return etag;
	}
}
