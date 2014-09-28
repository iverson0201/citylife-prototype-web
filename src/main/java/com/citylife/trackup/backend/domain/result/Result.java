package com.citylife.trackup.backend.domain.result;


/**
 * @author 作者 E-mail:xujw0201@gmail.com
 * @version 创建时间：2014年9月22日 下午4:45:34
 * @param <T>
 * @param <T>
 */
public class Result<T> {

	@SuppressWarnings("unused")
	private Integer code;
	private T obj;
	
	public Integer getCode() {
		return 1;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public T getObj() {
		return obj;
	}
	public void setObj(T obj) {
		this.obj = obj;
	}
	
}
