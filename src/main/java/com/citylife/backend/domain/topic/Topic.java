package com.citylife.backend.domain.topic;

import java.util.ArrayList;
import java.util.List;

import com.citylife.backend.domain.Base;
import com.citylife.backend.domain.person.Praise;
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
	
	private Address address;
	
	private List<Praise> praises = new ArrayList<Praise>();
	private int replyCount;
	
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
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public List<Praise> getPraises() {
		return praises;
	}
	public void setPraises(List<Praise> praises) {
		this.praises = praises;
	}
	public int getReplyCount() {
		return replyCount;
	}
	public void setReplyCount(int replyCount) {
		this.replyCount = replyCount;
	}
	public void setTopicReplyCount() {
		// TODO Auto-generated method stub
		this.replyCount += 1;
	}
}
