package com.citylife.trackup.backend.domain.user;


import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.data.annotation.Transient;

import com.citylife.trackup.backend.domain.Base;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * 用户
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class User extends Base {
    
    @NotNull
    private String tel;
    
    @NotNull
    private String username;
    
    @NotNull
    private String password;
    
    //角色 1:管理员 2:普通用户
    @NotNull
    private Integer role;
   
    //账户状态 0:正常使用 1:停用
    @NotNull
    private Integer state;
    @Transient
    private String session;
    //关注
    private List<Follow> follows = new ArrayList<Follow>();
    //粉丝
    private List<Follower> followers = new ArrayList<Follower>();
    
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

	public User() {
	}
    
	public User(String tel, String username, String password, Integer role,
			Integer state, String session) {
		super();
		this.tel = tel;
		this.username = username;
		this.password = password;
		this.role = role;
		this.state = state;
		this.session = session;
	}



	public String getSession() {
		return session;
	}

	public void setSession(String session) {
		this.session = session;
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

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	
}
