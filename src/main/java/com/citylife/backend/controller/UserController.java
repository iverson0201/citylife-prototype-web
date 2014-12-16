package com.citylife.backend.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
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

import com.citylife.backend.common.Constant;
import com.citylife.backend.common.Utils;
import com.citylife.backend.common.mapper.BeanMapper;
import com.citylife.backend.common.web.MediaTypes;
import com.citylife.backend.domain.result.Result;
import com.citylife.backend.domain.user.Follow;
import com.citylife.backend.domain.user.Follower;
import com.citylife.backend.domain.user.ThirdUser;
import com.citylife.backend.domain.user.User;
import com.citylife.backend.dto.ThirdUserDto;
import com.citylife.backend.dto.UserDto;
import com.citylife.backend.exception.NotFoundException;
import com.citylife.backend.exception.RestException;
import com.citylife.backend.im.comm.Constants;
import com.citylife.backend.im.comm.HTTPMethod;
import com.citylife.backend.im.comm.Roles;
import com.citylife.backend.im.httpclient.utils.HTTPClientUtils;
import com.citylife.backend.im.httpclient.vo.Credentail;
import com.citylife.backend.im.httpclient.vo.EndPoints;
import com.citylife.backend.im.httpclient.vo.UsernamePasswordCredentail;
import com.citylife.backend.service.UserService;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.common.base.Strings;

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
	private static JsonNodeFactory factory = new JsonNodeFactory(false);
	
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
        setUserDate(user);
        userService.insert(user);
        if (user != null) {
        	//创建用户名和密码的令牌  
    		UsernamePasswordToken token = new UsernamePasswordToken();
    		token.setUsername(user.getTel());  
    	    token.setPassword(user.getPassword().toCharArray());  
    		//记录该令牌，如果不记录则类似购物车功能不能使用。  
    		token.setRememberMe(true);  
    		//subject理解成权限对象。类似user  
    		Subject subject = SecurityUtils.getSubject();  
    		try {  
    		subject.login(token);  
    		} catch (UnknownAccountException ex) {//用户不存在。
    			ex.printStackTrace();
    			throw new RestException(Constant.ACCOUNT_NOT_EXIST);
    		} catch (IncorrectCredentialsException ex) {//用户名密码不匹配。
    			ex.printStackTrace();
    			throw new RestException(Constant.NAME_PWD_NOT_MATCH);
    		}catch (AuthenticationException e) {//其他的登录错误  
    			e.printStackTrace();
    			throw new RestException(Constant.OTHER_LOGIN_ERROR);
    		}  
        }
        //注册IM用户[单个]，给指定Constants.APPKEY创建一个新的用户
        ObjectNode objectNode = factory.objectNode();
		objectNode.put("username", user.getId());
		objectNode.put("password", user.getPassword());
		createNewIMUserSingle(objectNode);
		
        Result<UserDto> result = transformation(user);
    	return result;
	}
	private void setUserDate(User user) {
		Date date = new Date();
        user.setCreatedAt(date);
        user.setUpdatedAt(date);
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
            throw new IllegalArgumentException(Constant.USER_NOT_COMPLETE);
        }
        User loginUser = userService.login(user);
        if (loginUser != null) {
        	if(1 == loginUser.getState()){
        		throw new NotFoundException(Constant.USER_DISABLED);
        	}else{
        		//创建用户名和密码的令牌  
        		UsernamePasswordToken token = new UsernamePasswordToken();
        		token.setUsername(user.getTel());  
        	    token.setPassword(user.getPassword().toCharArray());  
        		//记录该令牌，如果不记录则类似购物车功能不能使用。  
        		token.setRememberMe(true);  
        		//subject理解成权限对象。类似user  
        		Subject subject = SecurityUtils.getSubject();  
        		try {  
        		subject.login(token);  
        		} catch (UnknownAccountException ex) {//用户不存在。
        			ex.printStackTrace();
        			throw new RestException(Constant.ACCOUNT_NOT_EXIST);
        		} catch (IncorrectCredentialsException ex) {//用户名密码不匹配。
        			ex.printStackTrace();
        			throw new RestException(Constant.NAME_PWD_NOT_MATCH);
        		}catch (AuthenticationException e) {//其他的登录错误  
        			e.printStackTrace();
        			throw new RestException(Constant.OTHER_LOGIN_ERROR);
        		}  
        		//验证是否成功登录的方法  
        		if (subject.isAuthenticated()) { 
        			Session session = subject.getSession();
        			System.out.println(session.getId());
        		} 
        	}
            
        } else {
            throw new NotFoundException(Constant.NAME_PWD_NOT_MATCH);
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
			logger.warn(Constant.ACCOUNT_NOT_EXIST);
			throw new RestException(Constant.ACCOUNT_NOT_EXIST);
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
     * 修改用户信息/账号绑定
     * @param user
     * @return
     */
    @RequestMapping(method = RequestMethod.PUT,consumes = MediaTypes.JSON)
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
    /**
     * 第三方登录
     * @param type
     * @param thirdId
     * @return
     */
    @RequestMapping(value = "/third/{thirdId}", method = RequestMethod.POST,consumes = MediaTypes.JSON)
    public Result<UserDto> thirdLogin(@RequestBody ThirdUserDto thirdUserDto,@PathVariable String thirdId){
    	User userRet = userService.findByThirdUser(thirdUserDto.getThirdType(),thirdId);
    	if(userRet == null){
    		userRet = new User();
    		setUserDate(userRet);
    		userRet.setRole(2);
    		userRet.setState(0);
    		ThirdUser thirdUser = new ThirdUser(thirdId,thirdUserDto.getThirdType());
    		userRet.getThirdUsers().add(thirdUser);
    		userRet.setUsername(thirdUserDto.getName());
    		userRet.setSex(thirdUserDto.getSex());
    		userRet.setHeadImage(thirdUserDto.getHeadImage());
    		userService.save(userRet);
    	}
    	Result<UserDto> result = transformation(userRet);
    	return result;
    }
    /**
     * 账号绑定
     * @param userId
     * @param type
     * @param thirdId
     * @return
     */
    @RequestMapping(value = "/{userId}/{type}/{thirdId}",method = RequestMethod.PUT,consumes = MediaTypes.JSON_UTF_8)
    public Result<UserDto> binding(@PathVariable String userId,@PathVariable Integer type,@PathVariable String thirdId){
    	User userRet = userService.findOne(userId);
    	if(userRet == null){
    		throw new RestException(Constant.ACCOUNT_NOT_EXIST);
    	}
    	List<ThirdUser> thirdUsers = userRet.getThirdUsers();
    	//QQ或是微信绑定手机号
    	if(type == 0 && thirdId != null){
    		User u = userService.findByPhoneNum(thirdId);
    		if(u != null && thirdId.intern() == u.getTel().intern()){
    			throw new RestException(Constant.PHONE_ALREADY_EXISTS);
    		}
    		userRet.setTel(thirdId);
    	}else if(type > 0 && thirdId != null){
    		if(thirdUsers == null){
    			throw new RestException(Constant.BIND_FAILD);
    		}
    		for(int i = 0 ; i <= thirdUsers.size()-1; i++){
    			if(thirdUsers.get(i).getThirdType() == type){
    				throw new RestException(Constant.NOT_BIND_MORE);
    			}
    			if(thirdUsers.get(i).getThirdId().intern() == thirdId.intern()){
    				throw new RestException(Constant.EXIST_BIND_USER);
    			}
    		}
    		ThirdUser thirdUser = new ThirdUser(thirdId,type);
    		userRet.getThirdUsers().add(thirdUser);
    	}
    	userService.update(userId, userRet);
    	Result<UserDto> result = transformation(userRet);
    	return result;
    }
    /**
     * 解除绑定
     * @param userId
     * @param type
     * @param thirdId
     * @return
     */
    @RequestMapping(value = "/remove/{userId}/{type}/{thirdId}",method = RequestMethod.PUT,consumes = MediaTypes.JSON)
    public Result<UserDto> unbinding(@PathVariable String userId,@PathVariable Integer type,@PathVariable String thirdId){
    	User userRet = userService.findOne(userId);
    	if(userRet == null){
    		throw new RestException("账号不存在。");
    	}
    	List<ThirdUser> thirdUsers = userRet.getThirdUsers();
    	if(thirdUsers == null || type <= 0 || thirdId == null){
    		throw new RestException("解除绑定失败。");
    	}
    	if(thirdUsers.size() == 1){
    		throw new RestException("不能解除绑定。");
    	}
    	for(int i = 0 ; i <= thirdUsers.size()-1; i++){
    		if(thirdUsers.get(i).getThirdId().intern() == thirdId.intern()){
    			userRet.getThirdUsers().remove(i);
    			break;
    		}
    	}
    	userService.update(userId, userRet);
    	Result<UserDto> result = transformation(userRet);
    	return result;
    }
    
    /**
	 * 注册IM用户[单个]
	 * 给指定Constants.APPKEY创建一个新的用户
	 * @param dataNode
	 * @return
	 */
	public ObjectNode createNewIMUserSingle(ObjectNode dataNode) {

		ObjectNode objectNode = factory.objectNode();
		// check Constants.APPKEY format
		if (!HTTPClientUtils.match("^(?!-)[0-9a-zA-Z\\-]+#[0-9a-zA-Z]+", Constants.APPKEY)) {
			logger.error("Bad format of Constants.APPKEY: " + Constants.APPKEY);
			objectNode.put("message", "Bad format of Constants.APPKEY");
			return objectNode;
		}

		objectNode.removeAll();
		// check properties that must be provided
		if (null != dataNode && !dataNode.has("username")) {
			logger.error("Property that named username must be provided .");

			objectNode.put("message", "Property that named username must be provided .");

			return objectNode;
		}
		if (null != dataNode && !dataNode.has("password")) {
			logger.error("Property that named password must be provided .");
			objectNode.put("message", "Property that named password must be provided .");
			return objectNode;
		}

		try {
			Credentail credentail = new UsernamePasswordCredentail(Constants.APP_ADMIN_USERNAME,
					Constants.APP_ADMIN_PASSWORD, Roles.USER_ROLE_APPADMIN);
			objectNode = HTTPClientUtils.sendHTTPRequest(EndPoints.USERS_URL, credentail, dataNode,
					HTTPMethod.METHOD_POST);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return objectNode;
	}
	/**
	 * 修改密码
	 * @param userId
	 * @param newpassword
	 * @param oldpassword
	 * @return
	 */
	@RequestMapping(value = "/update/{userId}/{newpassword}/{oldpassword}",method = RequestMethod.PUT,consumes = MediaTypes.JSON)
	public Result<UserDto> updatePwd(@PathVariable String userId,@PathVariable String newpassword,@PathVariable String oldpassword){
		User userRet = userService.findOne(userId);
		if(userRet == null){
			throw new RestException("账号不存在。");
		}else if(!userRet.getPassword().equals(oldpassword)){
			throw new RestException("旧密码不对，请重新输入旧密码。");
		}else{
			userRet.setPassword(newpassword);
			userService.update(userId, userRet);
		}
		Result<UserDto> result = transformation(userRet);
    	return result;
	}
	
}