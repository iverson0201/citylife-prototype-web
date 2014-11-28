package com.citylife.backend.domain.poi.beanty;

import com.citylife.backend.domain.Base;

/**
 * @author 作者 E-mail:xujw0201@gmail.com
 * @version 创建时间：2014年10月14日 下午4:48:02
 * 丽人
 */
public class Beauty extends Base{
	
	private String poiId;
	private String detail;
	private String hours;
	private String[] realPhotos;
	private String[] tags;

	private String service;
	private Boolean booking;
	private Boolean childSeat;
	private Boolean smokingArea;
	private Double perCapitaConsumption;
	public String getService() {
		return service;
	}
	public void setService(String service) {
		this.service = service;
	}
	public Boolean getBooking() {
		return booking;
	}
	public void setBooking(Boolean booking) {
		this.booking = booking;
	}
	public Boolean getChildSeat() {
		return childSeat;
	}
	public void setChildSeat(Boolean childSeat) {
		this.childSeat = childSeat;
	}
	public Boolean getSmokingArea() {
		return smokingArea;
	}
	public void setSmokingArea(Boolean smokingArea) {
		this.smokingArea = smokingArea;
	}
	public Double getPerCapitaConsumption() {
		return perCapitaConsumption;
	}
	public void setPerCapitaConsumption(Double perCapitaConsumption) {
		this.perCapitaConsumption = perCapitaConsumption;
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
