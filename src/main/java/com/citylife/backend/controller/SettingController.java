package com.citylife.backend.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.citylife.backend.common.web.MediaTypes;
import com.citylife.backend.domain.user.FeedBack;
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
	public String report(@RequestBody Report report){
		Date date = new Date();
		report.setCreatedAt(date);
		report.setUpdatedAt(date);
		reportService.CreateReport(report);
		return "{\"code\" : 1}";
	}
	/**
	 * 地图下载
	 * @return
	 */
	@RequestMapping(value = "/download",method = RequestMethod.GET,produces = MediaTypes.JSON_UTF_8)
	public String download(){
		String url = "http://legendtech-video.qiniudn.com/citylife.zip";
		return "{\"code\" : 1,\"url\" : \"" + url + "\"}";
	}
	/**
	 * 用户反馈
	 * @param feedBack
	 * @return
	 */
	@RequestMapping(value = "/feedBack",method = RequestMethod.POST,consumes = MediaTypes.JSON)
	public FeedBack feedBack(@RequestBody FeedBack feedBack){
		Date date = new Date();
		feedBack.setCreatedAt(date);
		feedBack.setUpdatedAt(date);
		reportService.createFeedBack(feedBack);
		return feedBack;
	}
}
