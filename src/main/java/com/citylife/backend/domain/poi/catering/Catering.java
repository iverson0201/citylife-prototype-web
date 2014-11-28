package com.citylife.backend.domain.poi.catering;

import com.citylife.backend.domain.Base;

/**
 * @author 作者 E-mail:xujw0201@gmail.com
 * @version 创建时间：2014年10月14日 下午4:46:45
 * 餐饮
 */
public class Catering extends Base{

	private String poiId;
	private String detail;
	private String hours;
	private String[] realPhotos;
	private String[] tags;
	
	private Boolean booking;
	private Boolean childSeat;
	private int parking;
	private Boolean smokingArea;
	private Menu menu;
	private Double perCapitaConsumption;
	private Boolean wifi;
	
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
	public int getParking() {
		return parking;
	}
	public void setParking(int parking) {
		this.parking = parking;
	}
	public Boolean getSmokingArea() {
		return smokingArea;
	}
	public void setSmokingArea(Boolean smokingArea) {
		this.smokingArea = smokingArea;
	}
	public Menu getMenu() {
		return menu;
	}
	public void setMenu(Menu menu) {
		this.menu = menu;
	}
	public Double getPerCapitaConsumption() {
		return perCapitaConsumption;
	}
	public void setPerCapitaConsumption(Double perCapitaConsumption) {
		this.perCapitaConsumption = perCapitaConsumption;
	}
	public Boolean getWifi() {
		return wifi;
	}
	public void setWifi(Boolean wifi) {
		this.wifi = wifi;
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
