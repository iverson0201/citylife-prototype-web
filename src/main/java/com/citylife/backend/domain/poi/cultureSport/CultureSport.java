package com.citylife.backend.domain.poi.cultureSport;

import com.citylife.backend.domain.Base;

/**
 * @author 作者 E-mail:xujw0201@gmail.com
 * @version 创建时间：2014年10月14日 下午4:48:57
 * 文体
 */
public class CultureSport extends Base{
	
	private String poiId;
	private String detail;
	private String hours;
	private String[] realPhotos;
	private String[] tags;

	private Boolean booking;
	private Boolean swimming;
	private String perCapitaConsumption;
	private Boolean wifi;
	private String facilitie;
	public Boolean getBooking() {
		return booking;
	}
	public void setBooking(Boolean booking) {
		this.booking = booking;
	}
	public Boolean getSwimming() {
		return swimming;
	}
	public void setSwimming(Boolean swimming) {
		this.swimming = swimming;
	}
	public String getPerCapitaConsumption() {
		return perCapitaConsumption;
	}
	public void setPerCapitaConsumption(String perCapitaConsumption) {
		this.perCapitaConsumption = perCapitaConsumption;
	}
	public Boolean getWifi() {
		return wifi;
	}
	public void setWifi(Boolean wifi) {
		this.wifi = wifi;
	}
	public String getFacilitie() {
		return facilitie;
	}
	public void setFacilitie(String facilitie) {
		this.facilitie = facilitie;
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
