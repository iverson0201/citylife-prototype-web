package com.citylife.trackup.backend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.citylife.trackup.backend.dao.QiniuDao;
import com.citylife.trackup.backend.domain.qiniu.Qiniu;
import com.citylife.trackup.backend.service.QiniuService;

/**
 * @author 作者 E-mail:xujw0201@gmail.com
 * @version 创建时间：2014年9月26日 下午5:05:25
 */
@Service
public class QiniuServiceImpl implements QiniuService {

	@Autowired
	private QiniuDao qiniuDao;
	
	@Override
	public void save(Qiniu qiniu) {
		// TODO Auto-generated method stub
		qiniuDao.save2Mongo(qiniu);
	}

	@Override
	public boolean existsQiniu(String scope) {
		// TODO Auto-generated method stub
		return qiniuDao.findByScope(scope);
	}

}
