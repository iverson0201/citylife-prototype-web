package com.citylife.backend.im.api;

import java.net.URL;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.citylife.backend.im.comm.Constants;
import com.citylife.backend.im.comm.HTTPMethod;
import com.citylife.backend.im.comm.Roles;
import com.citylife.backend.im.httpclient.utils.HTTPClientUtils;
import com.citylife.backend.im.httpclient.vo.Credentail;
import com.citylife.backend.im.httpclient.vo.EndPoints;
import com.citylife.backend.im.httpclient.vo.UsernamePasswordCredentail;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * REST API Demo :用户体系集成 REST API HttpClient4.3实现
 * Doc URL: http://www.easemob.com/docs/rest/userapi
 * @author Lynch 2014-09-15
 */
public class IMUsers {

	private static Logger LOGGER = LoggerFactory.getLogger(IMUsers.class);
	private static JsonNodeFactory factory = new JsonNodeFactory(false);

	/**
	 * 注册IM用户[单个]
	 * 给指定Constants.APPKEY创建一个新的用户
	 * @param dataNode
	 * @return
	 */
	public static ObjectNode createNewIMUserSingle(ObjectNode dataNode) {

		ObjectNode objectNode = factory.objectNode();
		// check Constants.APPKEY format
		if (!HTTPClientUtils.match("^(?!-)[0-9a-zA-Z\\-]+#[0-9a-zA-Z]+", Constants.APPKEY)) {
			LOGGER.error("Bad format of Constants.APPKEY: " + Constants.APPKEY);
			objectNode.put("message", "Bad format of Constants.APPKEY");
			return objectNode;
		}

		objectNode.removeAll();
		// check properties that must be provided
		if (null != dataNode && !dataNode.has("username")) {
			LOGGER.error("Property that named username must be provided .");

			objectNode.put("message", "Property that named username must be provided .");

			return objectNode;
		}
		if (null != dataNode && !dataNode.has("password")) {
			LOGGER.error("Property that named password must be provided .");
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
	 * 注册IM用户[批量]
	 * 给指定Constants.APPKEY创建一批用户
	 * @param dataArrayNode
	 * @return
	 */
	public static ObjectNode createNewIMUserBatch(ArrayNode dataArrayNode) {

		ObjectNode objectNode = factory.objectNode();
		// check Constants.APPKEY format
		if (!HTTPClientUtils.match("^(?!-)[0-9a-zA-Z\\-]+#[0-9a-zA-Z]+", Constants.APPKEY)) {
			LOGGER.error("Bad format of Constants.APPKEY: " + Constants.APPKEY);
			objectNode.put("message", "Bad format of Constants.APPKEY");
			return objectNode;
		}
		// check properties that must be provided
		if (dataArrayNode.isArray()) {
			for (JsonNode jsonNode : dataArrayNode) {
				if (null != jsonNode && !jsonNode.has("username")) {
					LOGGER.error("Property that named username must be provided .");
					objectNode.put("message", "Property that named username must be provided .");
					return objectNode;
				}
				if (null != jsonNode && !jsonNode.has("password")) {
					LOGGER.error("Property that named password must be provided .");
					objectNode.put("message", "Property that named password must be provided .");
					return objectNode;
				}
			}
		}

		try {
			Credentail credentail = new UsernamePasswordCredentail(Constants.APP_ADMIN_USERNAME,
					Constants.APP_ADMIN_PASSWORD, Roles.USER_ROLE_APPADMIN);
			objectNode = HTTPClientUtils.sendHTTPRequest(EndPoints.USERS_URL, credentail, dataArrayNode,
					HTTPMethod.METHOD_POST);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return objectNode;
	}


	/**
	 * 获取IM用户[主键查询]
	 * @param userPrimaryKey 用户主键：username或者uuid
	 * @return
	 */
	public static ObjectNode getIMUsersByPrimaryKey(String userPrimaryKey) {
		
		ObjectNode objectNode = factory.objectNode();
		// check Constants.APPKEY format
		if (!HTTPClientUtils.match("^(?!-)[0-9a-zA-Z\\-]+#[0-9a-zA-Z]+", Constants.APPKEY)) {
			LOGGER.error("Bad format of Constants.APPKEY: " + Constants.APPKEY);
			objectNode.put("message", "Bad format of Constants.APPKEY");
			return objectNode;
		}

		// check properties that must be provided
		if (StringUtils.isEmpty(userPrimaryKey)) {
			LOGGER.error("The primaryKey that will be useed to query must be provided .");
			objectNode.put("message", "The primaryKey that will be useed to query must be provided .");
			return objectNode;
		}

		try {
			Credentail credentail = new UsernamePasswordCredentail(Constants.APP_ADMIN_USERNAME,
					Constants.APP_ADMIN_PASSWORD, Roles.USER_ROLE_APPADMIN);
			URL userPrimaryUrl = HTTPClientUtils
					.getURL(Constants.APPKEY.replace("#", "/") + "/users/" + userPrimaryKey);
			objectNode = HTTPClientUtils.sendHTTPRequest(userPrimaryUrl, credentail, null, HTTPMethod.METHOD_GET);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return objectNode;
	}

	/**
	 * 获取IM用户[条件查询]
	 * @param username
	 * @return
	 */
	public static ObjectNode getIMUserByQueryStringNOPagenation(String username) {

		ObjectNode objectNode = factory.objectNode();
		// check Constants.APPKEY format
		if (!HTTPClientUtils.match("^(?!-)[0-9a-zA-Z\\-]+#[0-9a-zA-Z]+", Constants.APPKEY)) {
			LOGGER.error("Bad format of Constants.APPKEY: " + Constants.APPKEY);
			objectNode.put("message", "Bad format of Constants.APPKEY");
			return objectNode;
		}

		try {

			Credentail credentail = new UsernamePasswordCredentail(Constants.APP_ADMIN_USERNAME,
					Constants.APP_ADMIN_PASSWORD, Roles.USER_ROLE_APPADMIN);
			URL queryUserPrimaryUrl = HTTPClientUtils.getURL(Constants.APPKEY.replace("#", "/") + "/users"
					+ "?ql=select * where username=" + username);
			objectNode = HTTPClientUtils.sendHTTPRequest(queryUserPrimaryUrl, credentail, null, HTTPMethod.METHOD_GET);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return objectNode;
	}

	/**
	 * 删除IM用户[单个]
	 * 删除指定Constants.APPKEY下IM单个用户
	 * @param userPrimaryKey
	 * @return
	 */
	public static ObjectNode deleteIMUserByUserPrimaryKey(String userPrimaryKey) {
		
		ObjectNode objectNode = factory.objectNode();
		// check Constants.APPKEY format
		if (!HTTPClientUtils.match("^(?!-)[0-9a-zA-Z\\-]+#[0-9a-zA-Z]+", Constants.APPKEY)) {
			LOGGER.error("Bad format of Constants.APPKEY: " + Constants.APPKEY);
			objectNode.put("message", "Bad format of Constants.APPKEY");
			return objectNode;
		}

		try {
			Credentail credentail = new UsernamePasswordCredentail(Constants.APP_ADMIN_USERNAME,
					Constants.APP_ADMIN_PASSWORD, Roles.USER_ROLE_APPADMIN);
			URL deleteUserPrimaryUrl = HTTPClientUtils.getURL(Constants.APPKEY.replace("#", "/") + "/users/"
					+ userPrimaryKey);
			objectNode = HTTPClientUtils.sendHTTPRequest(deleteUserPrimaryUrl, credentail, null,
					HTTPMethod.METHOD_DELETE);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return objectNode;
	}

	/**
	 * 删除IM用户[批量]
	 * 批量指定Constants.APPKEY下删除IM用户
	 * @param limit
	 * @param queryStr
	 * @return
	 */
	public static ObjectNode deleteIMUserByUsernameBatch(Long limit, String queryStr) {

		ObjectNode objectNode = factory.objectNode();
		// check Constants.APPKEY format
		if (!HTTPClientUtils.match("^(?!-)[0-9a-zA-Z\\-]+#[0-9a-zA-Z]+", Constants.APPKEY)) {
			LOGGER.error("Bad format of Constants.APPKEY: " + Constants.APPKEY);
			objectNode.put("message", "Bad format of Constants.APPKEY");
			return objectNode;
		}
		if (StringUtils.isEmpty(queryStr)) {
			LOGGER.error("queryStr must be provided .");
			objectNode.put("message", "queryStr must be provided .");
			return objectNode;
		}

		try {
			Credentail credentail = new UsernamePasswordCredentail(Constants.APP_ADMIN_USERNAME,
					Constants.APP_ADMIN_PASSWORD, Roles.USER_ROLE_APPADMIN);
			URL deleteIMUserByUsernameBatchUrl = HTTPClientUtils.getURL(Constants.APPKEY.replace("#", "/") + "/users"
					+ "?ql=" + queryStr + "&limit=" + limit);
			objectNode = HTTPClientUtils.sendHTTPRequest(deleteIMUserByUsernameBatchUrl, credentail, null,
					HTTPMethod.METHOD_DELETE);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return objectNode;
	}

	/**
	 * 重置IM用户密码 提供原始密码
	 * 需提供username或者uuid,原始密码，新密码
	 * @param userPrimaryKey
	 * @param dataObjectNode
	 * @return
	 */
	public static ObjectNode modifyIMUserPasswordWithOldPasswd(String userPrimaryKey, ObjectNode dataObjectNode) {
		
		ObjectNode objectNode = factory.objectNode();
		// check Constants.APPKEY format
		if (!HTTPClientUtils.match("^(?!-)[0-9a-zA-Z\\-]+#[0-9a-zA-Z]+", Constants.APPKEY)) {
			LOGGER.error("Bad format of Constants.APPKEY: " + Constants.APPKEY);
			objectNode.put("message", "Bad format of Constants.APPKEY");
			return objectNode;
		}

		if (StringUtils.isEmpty(userPrimaryKey)) {
			LOGGER.error("Property that named userPrimaryKey must be provided，the value is username or uuid of imuser.");
			objectNode.put("message","Property that named userPrimaryKey must be provided，the value is username or uuid of imuser.");
			return objectNode;
		}

		if (null != dataObjectNode && !dataObjectNode.has("oldpassword")) {
			LOGGER.error("Property that named oldpassword must be provided .");
			objectNode.put("message", "Property that named oldpassword must be provided .");
			return objectNode;
		}

		if (null != dataObjectNode && !dataObjectNode.has("newpassword")) {
			LOGGER.error("Property that named newpassword must be provided .");
			objectNode.put("message", "Property that named newpassword must be provided .");
			return objectNode;
		}

		try {
			URL modifyIMUserPasswordWithOldPasswdUrl = HTTPClientUtils.getURL(Constants.APPKEY.replace("#", "/")
					+ "/users/" + userPrimaryKey + "/password");
			objectNode = HTTPClientUtils.sendHTTPRequest(modifyIMUserPasswordWithOldPasswdUrl, null, dataObjectNode,
					HTTPMethod.METHOD_PUT);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return objectNode;
	}

	/**
	 * 重置IM用户密码 提供管理员token
	 * @param userPrimaryKey
	 * @param dataObjectNode
	 * @return
	 */
	public static ObjectNode modifyIMUserPasswordWithAdminToken(String userPrimaryKey, ObjectNode dataObjectNode) {
		
		ObjectNode objectNode = factory.objectNode();
		// check Constants.APPKEY format
		if (!HTTPClientUtils.match("^(?!-)[0-9a-zA-Z\\-]+#[0-9a-zA-Z]+", Constants.APPKEY)) {
			LOGGER.error("Bad format of Constants.APPKEY: " + Constants.APPKEY);
			objectNode.put("message", "Bad format of Constants.APPKEY");
			return objectNode;
		}

		if (StringUtils.isEmpty(userPrimaryKey)) {
			LOGGER.error("Property that named userPrimaryKey must be provided，the value is username or uuid of imuser.");
			objectNode.put("message","Property that named userPrimaryKey must be provided，the value is username or uuid of imuser.");
			return objectNode;
		}

		if (null != dataObjectNode && !dataObjectNode.has("newpassword")) {
			LOGGER.error("Property that named newpassword must be provided .");
			objectNode.put("message", "Property that named newpassword must be provided .");
			return objectNode;
		}

		try {
			Credentail credentail = new UsernamePasswordCredentail(Constants.APP_ADMIN_USERNAME,
					Constants.APP_ADMIN_PASSWORD, Roles.USER_ROLE_APPADMIN);
			URL modifyIMUserPasswordWithAdminTokenUrl = HTTPClientUtils.getURL(Constants.APPKEY.replace("#", "/")
					+ "/users/" + userPrimaryKey + "/password");
			objectNode = HTTPClientUtils.sendHTTPRequest(modifyIMUserPasswordWithAdminTokenUrl, credentail,
					dataObjectNode, HTTPMethod.METHOD_PUT);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return objectNode;
	}
	
	
	/**
	 * IM用户登录
	 * @param ownerUserPrimaryKey 用户名
	 * @param friendUserPrimaryKeys 密码
	 * @return
	 */
	public static ObjectNode imUserLogin(String ownerUserPrimaryKey, String password) {

		ObjectNode objectNode = factory.objectNode();
		// check appKey format
		if (!HTTPClientUtils.match("^(?!-)[0-9a-zA-Z\\-]+#[0-9a-zA-Z]+", Constants.APPKEY)) {
			LOGGER.error("Bad format of Appkey: " + Constants.APPKEY);
			objectNode.put("message", "Bad format of Appkey");
			return objectNode;
		}
		
		if (StringUtils.isEmpty(ownerUserPrimaryKey)) {
			LOGGER.error("Your userPrimaryKey must be provided，the value is username or uuid of imuser.");
			objectNode.put("message", "Your userPrimaryKey must be provided，the value is username or uuid of imuser.");
			return objectNode;
		}
		if (StringUtils.isEmpty(password)) {
			LOGGER.error("Your password must be provided，the value is username or uuid of imuser.");
			objectNode.put("message", "Your password must be provided，the value is username or uuid of imuser.");
			return objectNode;
		}

		try {
			ObjectNode dataNode = factory.objectNode();
			dataNode.put("grant_type", "password");
			dataNode.put("username", ownerUserPrimaryKey);
			dataNode.put("password", password);

			objectNode = HTTPClientUtils.sendHTTPRequest(EndPoints.TOKEN_APP_URL, null, dataNode,
					HTTPMethod.METHOD_POST);
		} catch (Exception e) {
			throw new RuntimeException("Some errors ocuured while fetching a token by usename and passowrd .");
		}
		return objectNode;
	}

}
