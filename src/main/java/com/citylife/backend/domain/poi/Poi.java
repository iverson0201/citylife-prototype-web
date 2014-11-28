package com.citylife.backend.domain.poi;

import com.citylife.backend.domain.Base;

/**
 * @author 作者 E-mail:xujw0201@gmail.com
 * @version 创建时间：2014年11月13日 下午2:21:14
 */
public class Poi extends Base{

	/** 名称  */
	private String name;
	/** 地址  */
	private String address;
	/** 楼层  */
	private String floor;
	/** 电话  */
	private String phone;
	/** 类别编码  */
	private String code;
	/** 经度  */
	private Double lonx;
	/** 纬度  */
	private Double laty;
	
	/** 视点经度  */
	private Double viewpx;
	/** 视点纬度  */
	private Double viewpy;
	/** 视点高度  */
	private Double viewph;
	/** 视点方位角 */
	private Double viewpd;
	/** 视点倾斜角  */
	private Double viewpr;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getFloor() {
		return floor;
	}
	public void setFloor(String floor) {
		this.floor = floor;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
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
	public Double getViewpx() {
		return viewpx;
	}
	public void setViewpx(Double viewpx) {
		this.viewpx = viewpx;
	}
	public Double getViewpy() {
		return viewpy;
	}
	public void setViewpy(Double viewpy) {
		this.viewpy = viewpy;
	}
	public Double getViewph() {
		return viewph;
	}
	public void setViewph(Double viewph) {
		this.viewph = viewph;
	}
	public Double getViewpd() {
		return viewpd;
	}
	public void setViewpd(Double viewpd) {
		this.viewpd = viewpd;
	}
	public Double getViewpr() {
		return viewpr;
	}
	public void setViewpr(Double viewpr) {
		this.viewpr = viewpr;
	}
	
}
