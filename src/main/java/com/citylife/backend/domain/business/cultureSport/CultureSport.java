package com.citylife.backend.domain.business.cultureSport;

import com.citylife.backend.domain.Base;
import com.citylife.backend.domain.business.Business;

/**
 * @author 作者 E-mail:xujw0201@gmail.com
 * @version 创建时间：2014年10月14日 下午4:48:57
 * 文体
 */
public class CultureSport  extends Base{

	private Business business;
	private Boolean booking;
	private Boolean swimming;
	private String perCapitaConsumption;
	private Boolean wifi;
	private String facilitie;
	public Business getBusiness() {
		return business;
	}
	public void setBusiness(Business business) {
		this.business = business;
	}
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
	
}
