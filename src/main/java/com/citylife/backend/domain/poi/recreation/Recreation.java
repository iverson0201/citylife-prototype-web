package com.citylife.backend.domain.poi.recreation;

import com.citylife.backend.domain.Base;

/**
 * @author 作者 E-mail:xujw0201@gmail.com
 * @version 创建时间：2014年10月14日 下午4:47:38
 * 休闲娱乐
 */
public class Recreation extends Base{

	private String poiId;
	private String detail;
	private String hours;
	private String[] realPhotos;
	private String[] tags;
	
	private String sale;
	private Boolean childrenTicket;
	private String restArea;
	private String broadcastSchedule;
	private Boolean eyes3D;
	private Boolean groupPurchase;
	private Double perCapitaConsumption;
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
