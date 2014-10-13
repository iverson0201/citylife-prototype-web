package com.citylife.backend.dao;

import com.citylife.backend.domain.qiniu.Qiniu;

/**
 * @author 作者 E-mail:xujw0201@gmail.com
 * @version 创建时间：2014年9月26日 下午5:07:40
 */
public interface QiniuDao extends BaseDao<Qiniu, String> {

	boolean findByScope(String scope);

}
