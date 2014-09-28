package com.citylife.trackup.backend.service;

import com.citylife.trackup.backend.domain.qiniu.Qiniu;

/**
 * @author 作者 E-mail:xujw0201@gmail.com
 * @version 创建时间：2014年9月26日 下午5:05:12
 */
public interface QiniuService {

	void save(Qiniu qiniu);

	boolean existsQiniu(String scope);

}
