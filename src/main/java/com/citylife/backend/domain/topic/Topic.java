package com.citylife.backend.domain.topic;

import com.citylife.backend.domain.Base;
import com.citylife.backend.domain.person.Pubulisher;

/**
 * @author 作者 E-mail:xujw0201@gmail.com
 * @version 创建时间：2014年9月29日 下午1:59:59
 * 
 * 话题
 */
public class Topic extends Base{

	private String title;
	private String content;
	/** 图片 */
	private String[] images;
	/** 发布人 */
	private Pubulisher pubulisher;
	
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String[] getImages() {
		return images;
	}
	public void setImages(String[] images) {
		this.images = images;
	}
	public Pubulisher getPubulisher() {
		return pubulisher;
	}
	public void setPubulisher(Pubulisher pubulisher) {
		this.pubulisher = pubulisher;
	}
	
}
