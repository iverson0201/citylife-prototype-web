package com.citylife.trackup.backend.domain.qiniu;
/**
 * @author 作者 E-mail:xujw0201@gmail.com
 * @version 创建时间：2014年9月26日 下午4:38:00
 */
public class ReturnBody {

	//{"name":"daohanglan@2x.png","w":480,"size":214513,"h":640,
	//"hash":"Fh8xVqod2MQ1mocfI4S4KpRL6D98"},
	
	private String name;
	private Integer w;
	private Integer size;
	private Integer h;
	private String hash;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getW() {
		return w;
	}
	public void setW(Integer w) {
		this.w = w;
	}
	public Integer getSize() {
		return size;
	}
	public void setSize(Integer size) {
		this.size = size;
	}
	public Integer getH() {
		return h;
	}
	public void setH(Integer h) {
		this.h = h;
	}
	public String getHash() {
		return hash;
	}
	public void setHash(String hash) {
		this.hash = hash;
	}
	
	
}
