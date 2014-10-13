package com.citylife.backend.controller;

import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.citylife.backend.common.Utils;
import com.citylife.backend.domain.result.Result;
import com.citylife.backend.domain.topic.Topic;
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
	
	@RequestMapping(value = "/publish",method = RequestMethod.POST)
	public Result<Topic> create(@RequestBody @Valid Topic topic,BindingResult bindingResult){
		Result<Topic> result = new Result<Topic>();
		if(bindingResult.hasErrors()){
			throw new IllegalArgumentException(Utils.parseErrors(bindingResult.getFieldErrors()));
		}
		Date date = new Date();
		topic.setCreatedAt(date);
		topic.setUpdatedAt(date);
		topicService.insertTopic(topic);
		return result;
	}
}
