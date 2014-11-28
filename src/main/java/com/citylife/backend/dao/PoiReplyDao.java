package com.citylife.backend.dao;

import java.util.List;

import com.citylife.backend.domain.poi.PoiReply;

/**
 * @author 作者 E-mail:xujw0201@gmail.com
 * @version 创建时间：2014年10月15日 下午4:50:46
 */
public interface PoiReplyDao extends BaseDao<PoiReply, String>{

	List<PoiReply> findList(String commentId, int size, int page);

}
