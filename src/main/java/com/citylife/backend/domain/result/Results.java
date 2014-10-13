package com.citylife.backend.domain.result;

import java.util.List;

/**
 * @author 作者 E-mail:xujw0201@gmail.com
 * @version 创建时间：2014年9月22日 下午4:57:52
 * @param <T>
 */
public class Results<T> {

	private Integer code;
	private List<T> list;
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
	}
	
}
