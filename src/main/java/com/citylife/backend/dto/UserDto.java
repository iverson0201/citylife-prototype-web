package com.citylife.backend.dto;

import java.util.List;

import com.citylife.backend.domain.user.Follow;
import com.citylife.backend.domain.user.Follower;
import com.citylife.backend.domain.user.ThirdUser;


/**
 * @author 作者 E-mail:xujw0201@gmail.com
 * @version 创建时间：2014年9月22日 下午5:16:45
 */
public class UserDto {

		private String id;
	    private String tel;
	    private String username;
	    private String password;
	    
	    //关注
	    private List<Follow> follows;
	    //粉丝
	    private List<Follower> followers;
//	    private String thirdId;
//		private int thirdType;//1:QQ；2:微信；3：微博
		
		private Integer sex;//0：男；1：女
		private String headImage;
	    
		private List<ThirdUser> thirdUsers;
		
		public Integer getSex() {
			return sex;
		}
		public void setSex(Integer sex) {
			this.sex = sex;
		}
		public String getHeadImage() {
			return headImage;
		}
		public void setHeadImage(String headImage) {
			this.headImage = headImage;
		}
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
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public List<ThirdUser> getThirdUsers() {
			return thirdUsers;
		}
		public void setThirdUsers(List<ThirdUser> thirdUsers) {
			this.thirdUsers = thirdUsers;
		}
	    
}
