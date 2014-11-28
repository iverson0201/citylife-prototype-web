package com.citylife.backend.domain.result;

import java.util.List;


/**
 * @author 作者 E-mail:xujw0201@gmail.com
 * @version 创建时间：2014年9月22日 下午4:57:52
 * @param <T>
 */
public class Results<T> {

	@SuppressWarnings("unused")
	private Integer code;
	private List<T> list;
	private long total;
	
	public Integer getCode() {
		return 1;
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
	public long getTotal() {
		return total;
	}
	public void setTotal(long total) {
		this.total = total;
	}
	
}
