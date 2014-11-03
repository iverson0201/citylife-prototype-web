package com.citylife.backend.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.citylife.backend.common.web.MediaTypes;
import com.citylife.backend.domain.user.Report;
import com.citylife.backend.service.SettingService;

/**
 * @author 作者 E-mail:xujw0201@gmail.com
 * @version 创建时间：2014年10月24日 上午10:57:29
 * 用户、设置 相关
 */
@RestController
@RequestMapping(value = "/api/v1/setting")
public class SettingController {

	@Autowired
	private SettingService reportService;
	
	/**
	 * 用户举报
	 * @param report
	 * @return
	 */
	@RequestMapping(value = "/report",method = RequestMethod.POST,consumes = MediaTypes.JSON)
	private String report(@RequestBody Report report){
		Date date = new Date();
		report.setCreatedAt(date);
		report.setUpdatedAt(date);
		reportService.CreateReport(report);
		return "{\"code\" : 1}";
	}
}
