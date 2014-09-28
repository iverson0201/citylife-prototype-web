package com.citylife.trackup.backend.dto;

import com.citylife.trackup.backend.domain.qiniu.ReturnBody;

/**
 * @author 作者 E-mail:xujw0201@gmail.com
 * @version 创建时间：2014年9月26日 下午5:11:49
 */
public class QiniuDto {

	private long deadline;
	private ReturnBody returnBody;
	private String scope;
	public long getDeadline() {
		return deadline;
	}
	public void setDeadline(long deadline) {
		this.deadline = deadline;
	}
	public ReturnBody getReturnBody() {
		return returnBody;
	}
	public void setReturnBody(ReturnBody returnBody) {
		this.returnBody = returnBody;
	}
	public String getScope() {
		return scope;
	}
	public void setScope(String scope) {
		this.scope = scope;
	}
	
	
}
