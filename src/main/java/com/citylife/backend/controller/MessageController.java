package com.citylife.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.citylife.backend.common.Constant;
import com.citylife.backend.common.Utils;
import com.citylife.backend.common.web.MediaTypes;
import com.citylife.backend.domain.user.User;
import com.citylife.backend.exception.RestException;
import com.citylife.backend.service.MessageService;
import com.citylife.backend.service.UserService;

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
	
	@Autowired
    private UserService userService;
	
	/**
	 * 用户注册，发送短信验证码
	 * @param phone
	 * @return
	 */
	@RequestMapping(value = "/{phone}",method = RequestMethod.GET,produces = MediaTypes.JSON_UTF_8)
	public String identifyingCode(@PathVariable String phone){
		if (userService.findByPhoneNum(phone) != null) {
            throw new RestException(Constant.EXIST_PHONE);
        }
		int randomNumber = Utils.randomNumber();
		Boolean flag = messageService.sendShortMessage(phone,randomNumber);
		if(!flag){
			throw new RestException(Constant.MSG_FAILED);
		}
		return "{\"code\" : 1,\"randomNumber\" : \"" + randomNumber + "\"}";
	}
	/**
	 * 发送短信，重置密码
	 * @param phone
	 * @return
	 */
	@RequestMapping(value = "/reset/{phone}",method = RequestMethod.GET,produces = MediaTypes.JSON_UTF_8)
	public String reset(@PathVariable String phone){
		User user = userService.findByPhoneNum(phone);
		if(user == null){
			throw new RestException(Constant.ACCOUNT_NOT_EXIST);
		}
		int randomNumber = Utils.randomNumber();
		Boolean flag = messageService.sendShortMessage(phone,randomNumber);
		if(!flag){
			throw new RestException(Constant.MSG_FAILED);
		}
		return "{\"code\" : 1,\"userId\" : \"" + user.getId() + "\",\"randomNumber\" : \"" + randomNumber + "\"}";
	}
}
