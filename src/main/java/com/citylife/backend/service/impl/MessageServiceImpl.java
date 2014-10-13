package com.citylife.backend.service.impl;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.citylife.backend.common.ShortMessageClient;
import com.citylife.backend.exception.OperateFailedException;
import com.citylife.backend.service.MessageService;

/**
 * @author 作者 E-mail:xujw0201@gmail.com
 * @version 创建时间：2014年9月23日 下午1:39:19
 */
@Service
public class MessageServiceImpl implements MessageService {

	@Value("#{configs['message.sn']}")
	private String sn;
	@Value("#{configs['message.pwd']}")
	private String pwd;
	
	@Override
	public Boolean sendShortMessage(String phone,Integer randomNumber) {
		try {
			String content = "验证码：" + randomNumber + "【诚信】";
			content = URLEncoder.encode(content, "utf8");
			ShortMessageClient shortMessageClient = new ShortMessageClient(sn, pwd);
			String result_mt = shortMessageClient.mdSmsSend_u(phone, content, "", "", "");
			if(result_mt.startsWith("-") || result_mt.intern() == ""){
				return false;
			}else{
				return true;
			}
		} catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
			throw new OperateFailedException("服务器繁忙，稍后重试!");
		}
	}

}
