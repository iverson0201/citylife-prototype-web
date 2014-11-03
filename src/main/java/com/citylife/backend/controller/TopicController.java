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
import com.citylife.backend.domain.person.Backer;
import com.citylife.backend.domain.person.Praise;
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
	 *  获取某个话题详情
	 * @param topicId
	 * @return
	 */
	@RequestMapping(value = "/{topicId}",method = RequestMethod.GET,produces = MediaTypes.JSON_UTF_8)
	public Result<TopicDto> detail(@PathVariable String topicId){
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
	@RequestMapping(value = "/reply/{topicId}",method = RequestMethod.POST,consumes = MediaTypes.JSON)
	public Result<TopicReplyDto> reply(@RequestBody TopicReply topicReply,@PathVariable String topicId,BindingResult results){
		if (results.hasErrors()) {
            throw new IllegalArgumentException(Utils.parseErrors(results.getFieldErrors()));
        }
		Date date = new Date();
		topicReply.setCreatedAt(date);
		topicReply.setUpdatedAt(date);
		topicReply.setTopicId(topicId);
		topicReplyService.insert(topicReply);
		Topic topic = topicService.getTopic(topicId);
		topic.setTopicReplyCount();
		topicService.updateTopic(topicId, topic);
		return extractedReply(topicReply);
	}
	/**
	 * 删除回复 
	 * @param replyId
	 * @return
	 */
	@RequestMapping(value = "/reply/{replyId}",method = RequestMethod.DELETE,produces = MediaTypes.JSON_UTF_8)
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
	@RequestMapping(value = "/list",method = RequestMethod.GET,produces = MediaTypes.JSON_UTF_8)
	public Results<TopicDto> getTopics(
	@RequestParam(value = "size",required = false,defaultValue = "10") Integer size,
	@RequestParam(value = "page",required = false,defaultValue = "1") int page,
	@RequestParam(value = "sort",required = false,defaultValue = "updateAt") String sort, 
	@RequestParam(value = "order",required = false,defaultValue = "DESC") String order ){
		Assert.isTrue(page > 0, "Page index must be greater than 0");
        Assert.isTrue(size > 0, "Size must be greater than 0");
        Assert.isTrue("DESC".intern() == order.intern() || "ASC".intern() == order.intern(), "The value of order must be 'DESC' or 'ASC'");
		List<Topic> topics = topicService.getTopics(size,page,sort,order);
		List<TopicDto> dtos = BeanMapper.mapList(topics, TopicDto.class);
		Results<TopicDto> results = new Results<TopicDto>();
		results.setList(dtos);
        return results;
	}
	
	/**
	 * 跟帖
	 * @param topicReply
	 * 
	 * @return
	 */
	@RequestMapping(value = "/follow/{topicId}",method = RequestMethod.POST,consumes = MediaTypes.JSON)
	public Result<TopicReplyDto> replyFollow(@RequestBody TopicReply topicReply,@PathVariable String topicId,
			@RequestParam String userId,@RequestParam String userName,@RequestParam String headImage,BindingResult bindingResult){
		if(bindingResult.hasErrors()){
			throw new IllegalArgumentException(Utils.parseErrors(bindingResult.getFieldErrors()));
		}
		Date date = new Date();
		topicReply.setCreatedAt(date);
		topicReply.setUpdatedAt(date);
		topicReply.setTopicId(topicId);
		Backer backer = new Backer(userId,userName,headImage);
		topicReply.setBacker(backer);
		topicReplyService.insert(topicReply);
		Topic topic = topicService.getTopic(topicId);
		topic.setTopicReplyCount();
		topicService.updateTopic(topicId, topic);
		return extractedReply(topicReply);
	}
	
	private Result<TopicReplyDto> extractedReply(TopicReply topicReply) {
		TopicReplyDto topicReplyDto = BeanMapper.map(topicReply, TopicReplyDto.class);
		Result<TopicReplyDto> result = new Result<TopicReplyDto>();
		result.setObj(topicReplyDto);
		return result;
	}
	
	/**
	 * 话题点赞 | 取消点赞
	 * @param topicId
	 * @param praise
	 * @param results
	 * @return
	 */
	@RequestMapping(value = "/praise/{topicId}",method = RequestMethod.PUT,consumes = MediaTypes.JSON)
	public Result<TopicDto> test(@PathVariable String topicId, @RequestBody Praise praise,BindingResult results){
		if (results.hasErrors()) {
            throw new IllegalArgumentException(Utils.parseErrors(results.getFieldErrors()));
        }
		Topic topic = topicService.getTopic(topicId);
		boolean flag = false;
		int i = 0;
		List<Praise> praises = topic.getPraises();
		if(praises == null || praises.size() == 0){
			flag = true;
		}
		for(; i <= praises.size() - 1 && !flag; i++){
			if(praises.get(i).getUserId().intern() == praise.getUserId().intern()){
				praises.remove(i);
				i = -1;
				break;
			}
		}
		if(i > 0){
			flag = true;
		}
		if(flag){
			praises.add(praise);
		}
		topic.setPraises(praises);
		Topic topicRet = topicService.updateTopic(topicId, topic);
		return extracted(topicRet);
	}
	
	/**
	 * 回复列表
	 * @param topicId
	 * @param size
	 * @param page
	 * @return
	 */
	@SuppressWarnings("null")
	@RequestMapping(value = "/reply/{topicId}",method = RequestMethod.GET,produces = MediaTypes.JSON_UTF_8)
	public Results<TopicReply> replyList(@PathVariable String topicId,
			@RequestParam(value = "size",required = false,defaultValue = "5") int size,
			@RequestParam(value = "page",required = false,defaultValue = "1") int page){
		List<TopicReply> topicReplies = topicReplyService.getTopicReplys(topicId,size,page);
		if(topicReplies == null && topicReplies.size() == 0){
			throw new RestException("没有回复");
		}
		Results<TopicReply> results = new Results<TopicReply>();
		results.setList(topicReplies);
		return results;
	}

	@RequestMapping(value = "/test",method = RequestMethod.GET,consumes = MediaTypes.JSON_UTF_8)
	public String test(@RequestHeader(value = "nihao") String etag){
		return etag;
	}
}
