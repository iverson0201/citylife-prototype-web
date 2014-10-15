package com.citylife.backend.domain.business;
/**
 * @author 作者 E-mail:xujw0201@gmail.com
 * @version 创建时间：2014年10月14日 下午4:57:33
 */
public class Business {

	private String poiId;
	private String name;
	private String detail;
	private String address;
	private String phone;
	private String[] realPhotos;
	private String hours;
	private Double lonx;
	private Double laty;
	private String code;
	private String[] tags;
	public String getPoiId() {
		return poiId;
	}
	public void setPoiId(String poiId) {
		this.poiId = poiId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String[] getRealPhotos() {
		return realPhotos;
	}
	public void setRealPhotos(String[] realPhotos) {
		this.realPhotos = realPhotos;
	}
	public String getHours() {
		return hours;
	}
	public void setHours(String hours) {
		this.hours = hours;
	}
	public Double getLonx() {
		return lonx;
	}
	public void setLonx(Double lonx) {
		this.lonx = lonx;
	}
	public Double getLaty() {
		return laty;
	}
	public void setLaty(Double laty) {
		this.laty = laty;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String[] getTags() {
		return tags;
	}
	public void setTags(String[] tags) {
		this.tags = tags;
	}
	
}
