package com.citylife.backend.controller;

import java.util.Date;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.citylife.backend.common.Constant;
import com.citylife.backend.common.mapper.JsonMapper;
import com.citylife.backend.common.web.MediaTypes;
import com.citylife.backend.domain.qiniu.Qiniu;
import com.citylife.backend.domain.qiniu.ReturnBody;
import com.citylife.backend.dto.ResultDto;
import com.citylife.backend.exception.RestException;
import com.citylife.backend.service.QiniuService;
import com.qiniu.api.auth.AuthException;
import com.qiniu.api.auth.digest.Mac;
import com.qiniu.api.config.Config;
import com.qiniu.api.rs.PutPolicy;

/**
 * @author 作者 E-mail:xujw0201@gmail.com
 * @version 创建时间：2014年9月25日 下午4:59:41
 * 
 * 七牛云存储api
 */
@RestController
@RequestMapping(value = "/api/v1/qiniu")
public class QiniuController {

//	private Logger logger = LoggerFactory.getLogger(QiniuController.class);
	
	@Value("#{configs['access.key']}")
	private String accessKey;
	@Value("#{configs['secret.key']}")
	private String secretKey;
	
	@Autowired
	private QiniuService qiniuService;
	
	/**
	 * 生成上传授权token
	 * @param returnBody
	 * @return
	 */
	@RequestMapping(value = "/{fileName}/{type}",method = RequestMethod.POST,consumes = MediaTypes.JSON)
	public ResultDto uptoken(@RequestBody ReturnBody returnBody){
		String uptoken = null;
		Config.ACCESS_KEY = accessKey;
		Config.SECRET_KEY = secretKey;
		Mac mac = new Mac(Config.ACCESS_KEY, Config.SECRET_KEY);
		// 请确保该bucket已经存在
		PutPolicy putPolicy = new PutPolicy("legendtech-video");
		JsonMapper newMapper = JsonMapper.nonEmptyMapper();
		String returnBodyStr = newMapper.toJson(returnBody);
		putPolicy.returnBody = returnBodyStr;
		
		try {
			uptoken = putPolicy.token(mac);
			String scope = "legendtech-video:" + returnBody.getName();
			boolean flag = qiniuService.existsQiniu(scope);
			if(!flag){
				long deadline = ( System.currentTimeMillis() + 1 * 60 * 60 * 1000 ) / 1000;
				Qiniu qiniu = new Qiniu();
				qiniu.setReturnBody(returnBody);
				qiniu.setDeadline(deadline);
				qiniu.setScope(scope);
			   Date date = new Date();
			   qiniu.setCreatedAt(date);
			   qiniu.setUpdatedAt(date);
				qiniuService.save(qiniu);
			}
		} catch (JSONException e) {
			throw new RestException(Constant.UPTOKEN_JSON_EXCEPTION);
		} catch (AuthException e) {
			throw new RestException(Constant.UPTOKEN_AUTHEXCEPTION);
		}
		return new ResultDto(1,uptoken);
	}
}
