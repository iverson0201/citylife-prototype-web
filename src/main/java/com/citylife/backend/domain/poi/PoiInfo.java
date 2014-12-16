package com.citylife.backend.domain.poi;

import java.util.List;

import com.citylife.backend.domain.Base;

/**
 * @author 作者 E-mail:xujw0201@gmail.com
 * @version 创建时间：2014年12月2日 下午3:36:53
 */
public class PoiInfo extends Base{

	private PoiGenre poiGenre;
	private List<PoiServe> poiServes;
	private int status;//0:正常、1：置顶、2：禁用
	
	private String poiId;
	private String detail;
	private String hours;
	private String headImage;
	private String[] realPhotos;
	private String[] tags;
	
	public PoiGenre getPoiGenre() {
		return poiGenre;
	}
	public void setPoiGenre(PoiGenre poiGenre) {
		this.poiGenre = poiGenre;
	}
	public List<PoiServe> getPoiServes() {
		return poiServes;
	}
	public void setPoiServes(List<PoiServe> poiServes) {
		this.poiServes = poiServes;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
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
	public String getHeadImage() {
		return headImage;
	}
	public void setHeadImage(String headImage) {
		this.headImage = headImage;
	}
	
	
}
