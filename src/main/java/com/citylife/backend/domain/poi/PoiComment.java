package com.citylife.backend.domain.poi;

import com.citylife.backend.domain.Base;
import com.citylife.backend.domain.person.Comment;

/**
 * @author 作者 E-mail:xujw0201@gmail.com
 * @version 创建时间：2014年10月28日 下午6:01:56
 * 商家评论（点评）
 */
public class PoiComment extends Base{

	private String content;
	private String[] images;
	private Comment comment;
	private String poiId;
	private String poiImage;
	private String name;
	private String type;
	private String address;
	private String[] marks;
	private int replyCount;
	
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
	public Comment getComment() {
		return comment;
	}
	public void setComment(Comment comment) {
		this.comment = comment;
	}
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String[] getMarks() {
		return marks;
	}
	public void setMarks(String[] marks) {
		this.marks = marks;
	}
	public int getReplyCount() {
		return replyCount;
	}
	public void setReplyCount(int replyCount) {
		this.replyCount = replyCount;
	}
	public String getPoiImage() {
		return poiImage;
	}
	public void setPoiImage(String poiImage) {
		this.poiImage = poiImage;
	}
}
