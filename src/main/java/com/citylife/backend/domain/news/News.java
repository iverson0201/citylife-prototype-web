package com.citylife.backend.domain.news;

import io.searchbox.annotations.JestId;

/**
 * @author 作者 E-mail:xujw0201@gmail.com
 * @version 创建时间：2014年10月8日 下午3:57:50
 * 
 * 虚拟news 搜索文章
 */
public class News {

	@JestId
	private int id;
	private String title;
	private String content;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
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
	
	
}
