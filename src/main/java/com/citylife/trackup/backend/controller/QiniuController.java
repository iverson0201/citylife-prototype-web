package com.citylife.trackup.backend.controller;

import java.util.Date;

import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.citylife.trackup.backend.common.mapper.BeanMapper;
import com.citylife.trackup.backend.common.mapper.JsonMapper;
import com.citylife.trackup.backend.common.web.MediaTypes;
import com.citylife.trackup.backend.domain.qiniu.Qiniu;
import com.citylife.trackup.backend.domain.qiniu.ReturnBody;
import com.citylife.trackup.backend.dto.QiniuDto;
import com.citylife.trackup.backend.dto.ResultDto;
import com.citylife.trackup.backend.exception.RestException;
import com.citylife.trackup.backend.service.QiniuService;
import com.qiniu.api.auth.AuthException;
import com.qiniu.api.auth.digest.Mac;
import com.qiniu.api.config.Config;
import com.qiniu.api.rs.PutPolicy;

/**
 * @author 作者 E-mail:xujw0201@gmail.com
 * @version 创建时间：2014年9月25日 下午4:59:41
 */
@RestController
@RequestMapping(value = "/api/v1/qiniu")
public class QiniuController {

	private Logger logger = LoggerFactory.getLogger(QiniuController.class);
	
	@Value("#{configs['access.key']}")
	private String accessKey;
	@Value("#{configs['secret.key']}")
	private String secretKey;
	
	@Autowired
	private QiniuService qiniuService;
	/**
	 * 生成上传授权uptoken
	 * @param bucketName
	 * @return
	 */
	@RequestMapping(value = "/1/{fileName}/{type}",method = RequestMethod.PUT,consumes = MediaTypes.JSON)
	public String uptoken1(@RequestBody Qiniu qiniu){
		Config.ACCESS_KEY = accessKey;
		Config.SECRET_KEY = secretKey;
		Mac mac = new Mac(Config.ACCESS_KEY, Config.SECRET_KEY);
		// 请确保该bucket已经存在
		PutPolicy putPolicy = new PutPolicy("legendtech-video");
//		String returnBody = "{\"deadline\":1451491200,\"returnBody\":{\"name\":\"daohanglan@2x.png\",\"w\":480,\"size\":214513,\"h\":640,\"hash\":\"Fh8xVqod2MQ1mocfI4S4KpRL6D98\"},\"scope\":\"testcity:daohanglan@2x.png\"}";
		QiniuDto qiqiuDto = BeanMapper.map(qiniu, QiniuDto.class);
		JsonMapper newMapper = JsonMapper.nonEmptyMapper();
		String returnBody = newMapper.toJson(qiqiuDto);
		logger.info(returnBody);
		putPolicy.returnBody = returnBody;
		String uptoken = null;
		try {
			uptoken = putPolicy.token(mac);
			qiniuService.save(qiniu);
		} catch (JSONException e) {
			new RestException("获取uptoken错误，JSONException");
		} catch (AuthException e) {
			new RestException("获取uptoken错误，AuthException");
		}
		return "{\"code\" : 1,\"uptoken\" : " + uptoken + "}";
	}
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
			new RestException("获取uptoken错误，JSONException");
		} catch (AuthException e) {
			new RestException("获取uptoken错误，AuthException");
		}
		return new ResultDto(1,uptoken);
	}
}
