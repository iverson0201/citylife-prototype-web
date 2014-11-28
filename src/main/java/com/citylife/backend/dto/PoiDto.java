package com.citylife.backend.dto;

import com.citylife.backend.domain.poi.Poi;

/**
 * @author 作者 E-mail:xujw0201@gmail.com
 * @version 创建时间：2014年11月13日 下午3:21:24
 */
public class PoiDto {

	private Poi poi;
	private Object details;
	public Poi getPoi() {
		return poi;
	}
	public void setPoi(Poi poi) {
		this.poi = poi;
	}
	public Object getDetails() {
		return details;
	}
	public void setDetails(Object details) {
		this.details = details;
	}
	
}
