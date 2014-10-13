package com.citylife.backend.dto;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * @author 作者 E-mail:xujw0201@gmail.com
 * @version 创建时间：2014年9月28日 上午11:53:25
 */
public class ResultDto {

	private int code;
	private String str;
	
	public ResultDto() {
	}
	public ResultDto(int code, String str) {
		this.code = code;
		this.str = str;
	}
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getStr() {
		return str;
	}
	public void setStr(String str) {
		this.str = str;
	}
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	
}
