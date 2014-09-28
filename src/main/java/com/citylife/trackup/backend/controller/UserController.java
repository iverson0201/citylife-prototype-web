package com.citylife.trackup.backend.controller;

import java.util.Date;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.citylife.trackup.backend.auth.SessionKeys;
import com.citylife.trackup.backend.auth.SessionTokenProvider;
import com.citylife.trackup.backend.common.Utils;
import com.citylife.trackup.backend.common.mapper.BeanMapper;
import com.citylife.trackup.backend.common.web.MediaTypes;
import com.citylife.trackup.backend.domain.result.Result;
import com.citylife.trackup.backend.domain.user.Follow;
import com.citylife.trackup.backend.domain.user.Follower;
import com.citylife.trackup.backend.domain.user.User;
import com.citylife.trackup.backend.dto.UserDto;
import com.citylife.trackup.backend.exception.ExistException;
import com.citylife.trackup.backend.exception.NotFoundException;
import com.citylife.trackup.backend.exception.RestException;
import com.citylife.trackup.backend.service.SetupEnv;
import com.citylife.trackup.backend.service.UserService;
import com.google.common.base.Strings;
import com.google.common.collect.ImmutableMap;

/**
 * @author 作者 E-mail:xujw0201@gmail.com
 * @version 创建时间：2014年9月22日 上午10:50:04
 * 
 * 用户api
 */
@RestController
@RequestMapping("/api/v1/user")
class UserController {

	private Logger logger = LoggerFactory.getLogger(UserController.class);
	
    @Autowired
    private UserService userService;
    
    /**
     * 注册
     * @param user
     * @param results
     * @param response
     * @return
     */
    @ResponseStatus(value=HttpStatus.CREATED)// 按Restful风格约定，返回201状态码, 无内容. 也可以返回200状态码.
	@RequestMapping(value = "/register", method = RequestMethod.POST, consumes = MediaTypes.JSON)
	public Result<UserDto> create(@RequestBody @Valid User user, BindingResult results, HttpServletResponse response) {
        if (results.hasErrors()) {
            throw new IllegalArgumentException(Utils.parseErrors(results.getFieldErrors()));
        }
        if (userService.findByPhoneNum(user.getTel()) != null) {
            throw new ExistException("用户手机号已存在");
        }
        Date date = new Date();
        user.setCreatedAt(date);
        user.setUpdatedAt(date);
        userService.insert(user);
        if (user != null) {
            ImmutableMap<SessionKeys, ? extends Object> sessionObject = SessionTokenProvider.getSessionTokenProvider().setSession(SetupEnv.APP_KEY, user.getTel(), user.getPassword());
            response.setHeader(SessionKeys.TOKEN.toString(), (String) sessionObject.get(SessionKeys.TOKEN));
            user.setSession((String) sessionObject.get(SessionKeys.TOKEN));
        }
        Result<UserDto> result = transformation(user);
    	return result;
	}
    /**
     * 登录
     * @param user
     * @param response
     * @return
     */
    @RequestMapping(value ="/login", method = RequestMethod.POST)
    public Result<UserDto> login(@RequestBody User user, HttpServletResponse response) {
        if (Strings.isNullOrEmpty(user.getTel()) || Strings.isNullOrEmpty(user.getPassword())) {
            throw new IllegalArgumentException("用户信息不完整");
        }
        User loginUser = userService.login(user);
        if (loginUser != null) {
        	if(1 == loginUser.getState()){
        		throw new NotFoundException("该用户已被停用!");
        	}else{
        		ImmutableMap<SessionKeys, ? extends Object> sessionObject = SessionTokenProvider.getSessionTokenProvider().setSession(SetupEnv.APP_KEY, user.getTel(), user.getPassword());
                response.setHeader(SessionKeys.TOKEN.toString(), (String) sessionObject.get(SessionKeys.TOKEN));
                loginUser.setSession((String) sessionObject.get(SessionKeys.TOKEN));
        	}
            
        } else {
            throw new NotFoundException("用户不存在或密码不正确");
        }
        Result<UserDto> result = transformation(loginUser);
    	return result;
    }
    /**
     * 获取用户信息/获取用户粉丝/获取用户关注
     * @param userId
     * @return
     */
    @RequestMapping(value = "/{userId}",method = RequestMethod.GET,produces = MediaTypes.JSON_UTF_8)
    public Result<UserDto> get(@PathVariable String userId){
    	User user = userService.findOne(userId);
    	if(user == null){
    		String message = "用户不存在(id:" + userId + ")";
			logger.warn(message);
			throw new RestException(HttpStatus.NOT_FOUND,message);
    	}
    	// 使用Dozer转换DTO类，并补充Dozer不能自动绑定的属性
		Result<UserDto> result = transformation(user);
    	return result;
    }
    /**
     * 封装特定的dto对象
     * @param user
     * @return
     */
	private Result<UserDto> transformation(User user) {
		UserDto dto = BeanMapper.map(user, UserDto.class);
    	Result<UserDto> result = new Result<UserDto>();
    	result.setObj(dto);
		return result;
	}
    /**
     * 修改用户信息
     * @param user
     * @return
     */
    @RequestMapping(value ="/{userId}",method = RequestMethod.PUT,consumes = MediaTypes.JSON)
    public Result<UserDto> update(@RequestBody User user){
    	user.setUpdatedAt(new Date());
    	User userRet = userService.update(user.getId(), user);
    	Result<UserDto> result = transformation(userRet);
    	return result;
    }
    /**
     * 删除用户信息
     * @param userId
     * @return
     */
    @RequestMapping(value = "/{userId}",method = RequestMethod.DELETE)
    public String delete(@PathVariable String userId){
    	userService.delete(userId);
    	return "{\"code\" : 1}";
    }
    /**
     * 用户找回密码
     * @param userId
     * @param password
     * @return
     */
    @RequestMapping(value = "/{userId}/{password}",method = RequestMethod.PUT,consumes = MediaTypes.JSON)
    public Result<UserDto> findPwd(@PathVariable String userId,@PathVariable String password){
    	User userParam = new User();
    	userParam.setPassword(password);
    	User userRet = userService.update(userId, userParam);
    	Result<UserDto> result = transformation(userRet);
    	return result;
    }
    /**
     * 关注某人/取消关注某人
     * @param user
     * @param followId
     * @param followName
     * @return
     */
    @RequestMapping(value = "/{userId}/{followId}/{followName}/{flag}",method = RequestMethod.PUT,consumes = MediaTypes.JSON)
    public Result<UserDto> following(@PathVariable String userId,@PathVariable String followId,
    		@PathVariable String followName,@PathVariable Boolean flag){
    	User user = userService.findOne(userId);
    	//关注的人
    	Follow follow = new Follow();
		follow.setFollowId(followId);
		follow.setFollowName(followName);
		if(extractedFollow(user, flag, follow))
			userService.update(user.getId(), user);
		//关注的人的粉丝
		User userFollow = userService.findOne(followId);
		Follower follower = new Follower();
		follower.setFollowerId(user.getId());
		follower.setFollowerName(user.getUsername());
		if(extractedFollower(flag, userFollow, follower))
			userService.update(followId, userFollow);
		Result<UserDto> result = transformation(user);
    	return result;
    }
    /**
     * 添加粉丝和删除粉丝
     * @param flag
     * @param userFollow
     * @param follower
     * @return
     */
	private boolean extractedFollower(Boolean flag, User userFollow,
			Follower follower) {
		if(flag){
			if(userFollow.getFollowers().size() == 0){
				userFollow.getFollowers().add(follower);
				return true;
			}
			for (int i = 0; i < userFollow.getFollowers().size(); i++) {
				if(follower.getFollowerId().intern() == userFollow.getFollowers().get(i).getFollowerId().intern()){
					userFollow.getFollowers().add(follower);
					return true;
				}
			}
		}else{
			if(userFollow.getFollowers().size() == 0){
				return false;
			}
			for (int i = 0; i < userFollow.getFollowers().size(); i++) {
				if(follower.getFollowerId().intern() == userFollow.getFollowers().get(i).getFollowerId().intern()){
					userFollow.getFollowers().remove(i);	
					return true;
				}
			}
		}
		return false;
	}
    /**
     * 添加关注和删除关注
     * @param user
     * @param flag
     * @param follow
     * @return
     */
	private boolean extractedFollow(User user, Boolean flag, Follow follow) {
		if(flag){//关注
			if(user.getFollows().size() == 0){
				user.getFollows().add(follow);
				return true;
			}
			for (int i = 0; i < user.getFollows().size(); i++) {
				if(follow.getFollowId().intern() == user.getFollows().get(i).getFollowId().intern()){
					user.getFollows().add(follow);
					return true;
				}
			}
		}else{//取消关注
			if(user.getFollows().size() == 0){
				return false;
			}
			for (int i = 0; i < user.getFollows().size(); i++) {
				if(follow.getFollowId().intern() == user.getFollows().get(i).getFollowId().intern()){
					user.getFollows().remove(i);
					return true;
				}
			}
		}
		return false;
	}
	
    /**
     * 获取用户某个粉丝
     * @param followerId
     * @return
     */
    @RequestMapping(value = "/{followerId}/follower",method = RequestMethod.GET,produces = MediaTypes.JSON_UTF_8)
    public Result<UserDto> getFollower(@PathVariable String followerId){
    	User userFollower = userService.findOne(followerId);
    	Result<UserDto> result = transformation(userFollower);
    	return result;
    }
    /**
     * 获取用户某个关注
     * @param followId
     * @return
     */
    @RequestMapping(value = "/{followId}/follow",method = RequestMethod.GET,produces = MediaTypes.JSON_UTF_8)
    public Result<UserDto> getFollow(@PathVariable String followId){
    	User userFollow = userService.findOne(followId);
    	Result<UserDto> result = transformation(userFollow);
    	return result;
    }
}