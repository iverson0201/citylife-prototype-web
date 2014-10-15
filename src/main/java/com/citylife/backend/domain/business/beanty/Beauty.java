package com.citylife.backend.domain.business.beanty;

import com.citylife.backend.domain.Base;
import com.citylife.backend.domain.business.Business;

/**
 * @author 作者 E-mail:xujw0201@gmail.com
 * @version 创建时间：2014年10月14日 下午4:48:02
 * 丽人
 */
public class Beauty  extends Base{

	private Business business;
	private String service;
	private Boolean booking;
	private Boolean childSeat;
	private Boolean smokingArea;
	private Double perCapitaConsumption;
	public Business getBusiness() {
		return business;
	}
	public void setBusiness(Business business) {
		this.business = business;
	}
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
	
}
