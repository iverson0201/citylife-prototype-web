package com.citylife.backend.dto;

import com.citylife.backend.domain.poi.Poi;
import com.citylife.backend.domain.poi.PoiInfo;

/**
 * @author 作者 E-mail:xujw0201@gmail.com
 * @version 创建时间：2014年12月4日 下午5:41:27
 */
public class PoiInfoDto {

	private Poi poi;
	private PoiInfo poiInfo;
	public Poi getPoi() {
		return poi;
	}
	public void setPoi(Poi poi) {
		this.poi = poi;
	}
	public PoiInfo getPoiInfo() {
		return poiInfo;
	}
	public void setPoiInfo(PoiInfo poiInfo) {
		this.poiInfo = poiInfo;
	}
	public PoiInfoDto() {
		// TODO Auto-generated constructor stub
	}
	public PoiInfoDto(Poi poi, PoiInfo poiInfo) {
		this.poi = poi;
		this.poiInfo = poiInfo;
	}
	
}
