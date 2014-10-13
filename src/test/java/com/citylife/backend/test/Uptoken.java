package com.citylife.backend.test;

import org.json.JSONException;

import com.qiniu.api.auth.AuthException;
import com.qiniu.api.auth.digest.Mac;
import com.qiniu.api.config.Config;
import com.qiniu.api.rs.PutPolicy;

/**
 * @author 作者 E-mail:xujw0201@gmail.com
 * @version 创建时间：2014年9月25日 下午4:39:05
 */
public class Uptoken {

	public static void main(String[] args) throws JSONException, AuthException {
		Config.ACCESS_KEY = "z4WxLe9-_tbI_oCtbqSQId_F4gAUc2wJf_l12W0M";
		Config.SECRET_KEY = "HFaWHjB6MlenDwU1ofksuNiwI3NeqXc8wOa7jzaY";
		Mac mac = new Mac(Config.ACCESS_KEY, Config.SECRET_KEY);
		// 请确保该bucket已经存在
		String bucketName = "legendtech-video";
		PutPolicy putPolicy = new PutPolicy(bucketName);
//		putPolicy.returnBody = "{\"name\":daohanglan@2x.png}";
		String uptoken = putPolicy.token(mac);
		System.out.println("uptoken: " + uptoken);
	}
}
