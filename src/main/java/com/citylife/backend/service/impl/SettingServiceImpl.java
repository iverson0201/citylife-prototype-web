package com.citylife.backend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.citylife.backend.dao.ReportDao;
import com.citylife.backend.domain.user.Report;
import com.citylife.backend.service.SettingService;

/**
 * @author 作者 E-mail:xujw0201@gmail.com
 * @version 创建时间：2014年10月24日 上午11:01:09
 */
@Service
public class SettingServiceImpl implements SettingService {

	@Autowired
	private ReportDao reportDao;
	
	@Override
	public void CreateReport(Report report) {
		// TODO Auto-generated method stub
		reportDao.insert2Mongo(report);
	}

}
