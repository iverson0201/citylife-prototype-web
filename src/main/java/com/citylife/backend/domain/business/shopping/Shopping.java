package com.citylife.backend.domain.business.shopping;

import com.citylife.backend.domain.business.Business;

/**
 * @author 作者 E-mail:xujw0201@gmail.com
 * @version 创建时间：2014年10月14日 下午4:46:54
 * 购物
 */
public class Shopping extends Business{

	private Boolean tryOn;
	private String online;
	private String salesProduct;
	public Boolean getTryOn() {
		return tryOn;
	}
	public void setTryOn(Boolean tryOn) {
		this.tryOn = tryOn;
	}
	public String getOnline() {
		return online;
	}
	public void setOnline(String online) {
		this.online = online;
	}
	public String getSalesProduct() {
		return salesProduct;
	}
	public void setSalesProduct(String salesProduct) {
		this.salesProduct = salesProduct;
	}
	
}
