package com.citylife.backend.domain.poi.shopping;

import com.citylife.backend.domain.Base;

/**
 * @author 作者 E-mail:xujw0201@gmail.com
 * @version 创建时间：2014年10月14日 下午4:46:54
 * 购物
 */
public class Shopping extends Base{
	
	private String poiId;
	private String detail;
	private String hours;
	private String[] realPhotos;
	private String[] tags;

	private Boolean tryOn;
	private String online;
	private String salesProduct;
	public Boolean getTryOn() {
		return tryOn;
	}
	public void setTryOn(Boolean tryOn) {
		this.tryOn = tryOn;
	}
	public String getOnline() {
		return online;
	}
	public void setOnline(String online) {
		this.online = online;
	}
	public String getSalesProduct() {
		return salesProduct;
	}
	public void setSalesProduct(String salesProduct) {
		this.salesProduct = salesProduct;
	}
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
