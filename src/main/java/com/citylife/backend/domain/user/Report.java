package com.citylife.backend.domain.user;

import com.citylife.backend.domain.Base;



/**
 * @author 作者 E-mail:xujw0201@gmail.com
 * @version 创建时间：2014年10月24日 上午10:34:40
 * 举报
 */
public class Report extends Base {

	/** 举报人Id */
	private String userId;
	/** 举报内容Id （分享Id、评论Id） */
	private String contentId;
	/** 举报内容 */
	private String content;
	/** 举报类型 */
	private Integer reportType; //1：分享；2、评论
	/** 举报类别 */
	private Integer category; //1、广告欺诈；2、反动政治；3、色情暴力；4、恶意灌水；5、人身攻击；6、非法虚假信息；7、其他
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getContentId() {
		return contentId;
	}
	public void setContentId(String contentId) {
		this.contentId = contentId;
	}
	public Integer getReportType() {
		return reportType;
	}
	public void setReportType(Integer reportType) {
		this.reportType = reportType;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getCategory() {
		return category;
	}
	public void setCategory(Integer category) {
		this.category = category;
	}
	
}
