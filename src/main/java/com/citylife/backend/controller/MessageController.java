package com.citylife.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.citylife.backend.common.Utils;
import com.citylife.backend.exception.RestException;
import com.citylife.backend.service.MessageService;

/**
 * @author 作者 E-mail:xujw0201@gmail.com
 * @version 创建时间：2014年9月23日 下午1:26:33
 * 短信
 */
@RestController
@RequestMapping("/api/v1/message")
public class MessageController {

	@Autowired
	private MessageService messageService;
	
	/**
	 * 发送短信验证码
	 * @param phone
	 * @return
	 */
	@RequestMapping(value = "/{phone}",method = RequestMethod.GET)
	public String identifyingCode(@PathVariable String phone){
		int randomNumber = Utils.randomNumber();
		Boolean flag = messageService.sendShortMessage(phone,randomNumber);
		if(!flag){
			throw new RestException("短信发送失败。");
		}
		return "{\"code\" : 1,\"randomNumber\" : \"" + randomNumber + "\"}";
	}
}
