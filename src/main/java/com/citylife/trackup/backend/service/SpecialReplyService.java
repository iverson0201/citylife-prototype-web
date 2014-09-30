package com.citylife.trackup.backend.service;

import com.citylife.trackup.backend.domain.subject.SpecialReply;

/**
 * @author 作者 E-mail:xujw0201@gmail.com
 * @version 创建时间：2014年9月29日 下午5:50:21
 */
public interface SpecialReplyService {

	void save(SpecialReply specialReply);

	void deleteReply(String specialReplyId);

	boolean findReply(String specialReplyId);

}
