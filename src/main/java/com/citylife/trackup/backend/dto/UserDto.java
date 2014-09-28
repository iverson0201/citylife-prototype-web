package com.citylife.trackup.backend.dto;

import java.util.Date;
import java.util.List;

import com.citylife.trackup.backend.domain.user.Follow;
import com.citylife.trackup.backend.domain.user.Follower;


/**
 * @author 作者 E-mail:xujw0201@gmail.com
 * @version 创建时间：2014年9月22日 下午5:16:45
 */
public class UserDto {

		private String id;
	    private String tel;
	    private String username;
	    private String password;
	    //角色 1:管理员 2:普通用户
	    private Integer role;
	    //账户状态 0:正常使用 1:停用
	    private Integer state;
	    private Date createdAt;
	    private Date updatedAt;
	    
	    //关注
	    private List<Follow> follows;
	    //粉丝
	    private List<Follower> followers;
	    
		public List<Follow> getFollows() {
			return follows;
		}
		public void setFollows(List<Follow> follows) {
			this.follows = follows;
		}
		public List<Follower> getFollowers() {
			return followers;
		}
		public void setFollowers(List<Follower> followers) {
			this.followers = followers;
		}
		public String getTel() {
			return tel;
		}
		public void setTel(String tel) {
			this.tel = tel;
		}
		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		public Integer getRole() {
			return role;
		}
		public void setRole(Integer role) {
			this.role = role;
		}
		public Integer getState() {
			return state;
		}
		public void setState(Integer state) {
			this.state = state;
		}
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public Date getCreatedAt() {
			return createdAt;
		}
		public void setCreatedAt(Date createdAt) {
			this.createdAt = createdAt;
		}
		public Date getUpdatedAt() {
			return updatedAt;
		}
		public void setUpdatedAt(Date updatedAt) {
			this.updatedAt = updatedAt;
		}
	    
}
