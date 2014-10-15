package com.citylife.backend.domain.business.recreation;

import com.citylife.backend.domain.Base;
import com.citylife.backend.domain.business.Business;

/**
 * @author 作者 E-mail:xujw0201@gmail.com
 * @version 创建时间：2014年10月14日 下午4:47:38
 * 休闲娱乐
 */
public class Recreation extends Base{

	private Business business;
	private String sale;
	private Boolean childrenTicket;
	private String restArea;
	private String broadcastSchedule;
	private Boolean eyes3D;
	private Boolean groupPurchase;
	private Double perCapitaConsumption;
	public Business getBusiness() {
		return business;
	}
	public void setBusiness(Business business) {
		this.business = business;
	}
	public String getSale() {
		return sale;
	}
	public void setSale(String sale) {
		this.sale = sale;
	}
	public Boolean getChildrenTicket() {
		return childrenTicket;
	}
	public void setChildrenTicket(Boolean childrenTicket) {
		this.childrenTicket = childrenTicket;
	}
	public String getRestArea() {
		return restArea;
	}
	public void setRestArea(String restArea) {
		this.restArea = restArea;
	}
	public String getBroadcastSchedule() {
		return broadcastSchedule;
	}
	public void setBroadcastSchedule(String broadcastSchedule) {
		this.broadcastSchedule = broadcastSchedule;
	}
	public Boolean getEyes3D() {
		return eyes3D;
	}
	public void setEyes3D(Boolean eyes3d) {
		eyes3D = eyes3d;
	}
	public Boolean getGroupPurchase() {
		return groupPurchase;
	}
	public void setGroupPurchase(Boolean groupPurchase) {
		this.groupPurchase = groupPurchase;
	}
	public Double getPerCapitaConsumption() {
		return perCapitaConsumption;
	}
	public void setPerCapitaConsumption(Double perCapitaConsumption) {
		this.perCapitaConsumption = perCapitaConsumption;
	}
	
	
}
