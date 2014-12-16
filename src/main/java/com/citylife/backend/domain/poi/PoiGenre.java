package com.citylife.backend.domain.poi;

import com.citylife.backend.domain.Base;

/**
 * @author 作者 E-mail:xujw0201@gmail.com
 * @version 创建时间：2014年11月28日 下午3:58:08
 * poi分类
 */
public class PoiGenre extends Base{

	/** 类别编码  */
	private String code;
	private String name;
	private int sort;
	private String remark;
	private PoiGenre parentPoiGenre;
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getSort() {
		return sort;
	}
	public void setSort(int sort) {
		this.sort = sort;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public PoiGenre getParentPoiGenre() {
		return parentPoiGenre;
	}
	public void setParentPoiGenre(PoiGenre parentPoiGenre) {
		this.parentPoiGenre = parentPoiGenre;
	}
	
}
