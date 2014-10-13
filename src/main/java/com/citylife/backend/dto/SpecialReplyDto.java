package com.citylife.backend.dto;

import com.citylife.backend.domain.subject.SpecialReply;


/**
 * @author 作者 E-mail:xujw0201@gmail.com
 * @version 创建时间：2014年9月29日 上午10:11:16
 */
public class SpecialReplyDto {

	private String subjectId;
	private SpecialReply specialReply;
	
	
	public String getSubjectId() {
		return subjectId;
	}
	public void setSubjectId(String subjectId) {
		this.subjectId = subjectId;
	}
	public SpecialReply getSpecialReply() {
		return specialReply;
	}
	public void setSpecialReply(SpecialReply specialReply) {
		this.specialReply = specialReply;
	}
	
	
}
