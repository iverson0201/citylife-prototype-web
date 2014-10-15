package com.citylife.backend.domain.business.catering;

import com.citylife.backend.domain.business.Business;

/**
 * @author 作者 E-mail:xujw0201@gmail.com
 * @version 创建时间：2014年10月14日 下午4:46:45
 * 餐饮
 */
public class Catering extends Business{

	private Boolean booking;
	private Boolean childSeat;
	private Boolean parking;
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
	public Boolean getParking() {
		return parking;
	}
	public void setParking(Boolean parking) {
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
	
	
}
