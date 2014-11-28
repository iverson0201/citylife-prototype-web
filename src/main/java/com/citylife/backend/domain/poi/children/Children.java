package com.citylife.backend.domain.poi.children;

import com.citylife.backend.domain.Base;

/**
 * @author 作者 E-mail:xujw0201@gmail.com
 * @version 创建时间：2014年10月14日 下午4:49:42
 * 亲子
 */
public class Children extends Base{

	private String poiId;
	private String detail;
	private String hours;
	private String[] realPhotos;
	private String[] tags;
	public String getPoiId() {
		return poiId;
	}
	public void setPoiId(String poiId) {
		this.poiId = poiId;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public String getHours() {
		return hours;
	}
	public void setHours(String hours) {
		this.hours = hours;
	}
	public String[] getRealPhotos() {
		return realPhotos;
	}
	public void setRealPhotos(String[] realPhotos) {
		this.realPhotos = realPhotos;
	}
	public String[] getTags() {
		return tags;
	}
	public void setTags(String[] tags) {
		this.tags = tags;
	}
	
}
