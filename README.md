1、注册 POST
{
"tel" : "18612345672",
"username": "xujw01",
"password": "123",
"role" :2,
"state": 0
}
http://192.168.40.184:8080/citylife/api/v1/user/register

2、登录 POST
{
 "tel" : "18612345671",
 "password" : "123"
}
http://192.168.40.184:8080/citylife/api/v1/user/login

3、个人中心 GET
 "userId" : "541bf4de9fa52afb78246a26"
http://192.168.40.184:8080/citylife/api/v1/user/{userId}

4、删除用户信息 DELETE
 "userId" : "541f8ccf9fa56d12daa88128"
http://192.168.40.184:8080/citylife/api/v1/user/{userId}

5、修改用户信息 PUT
{
  "id" : "541bf4de9fa52afb78246a26",
  "tel" : "18612345678",
  "username" : "xujw002"
}
http://192.168.40.184:8080/citylife/api/v1/user/{userId}

6、用户找回密码 PUT
 "userId" : "541f7cc19fa561f6b8b3ae64",
 "password" : "1234"
http://192.168.40.184:8080/citylife/api/v1/user/{userId}/{newpassword}

7、发送短信验证码 GET
 "phone" : "18610775451"
http://192.168.40.184:8080/citylife/api/v1/message/{phone}

8、关注某人 PUT
  "userId" : "541bf4de9fa52afb78246a26",
  "followId" : "5420cf789fa52c7ee9dba8eb",
  "followName" : "xujw01",
  "flag" : false
http://192.168.40.184:8080/citylife/api/v1/user/{userId}/{followId}/{followName}/{flag}

9、取消关注某人 PUT
  "userId" : "541bf4de9fa52afb78246a26",
  "followId" : "5420cf789fa52c7ee9dba8eb",
  "followName" : "xujw01",
  "flag" : true
http://192.168.40.184:8080/citylife/api/v1/user/{userId}/{followId}/{followName}/{flag}

10、云存储，生成上传授权uptoken POST
{
"name" : "daohanglan@2x.png",
"w" : 480,
"size" : 214513,
"h" : 640,
"hash" : "Fh8xVqod2MQ1mocfI4S4KpRL6D98"
}
http://192.168.40.184:8080/citylife/api/v1/qiniu/{fileName}/{type}

11、获取用户所有粉丝 GET
 "userId" : "541bf4de9fa52afb78246a26"
http://192.168.40.184:8080/citylife/api/v1/user/{userId}

12、获取用户某个粉丝 GET
 "followerId" : "5420cf789fa52c7ee9dba8eb"
http://192.168.40.184:8080/citylife/api/v1/user/{followerId}/follower

13、获取用户所有关注 GET
 "userId" : "541bf4de9fa52afb78246a26"
http://192.168.40.184:8080/citylife/api/v1/user/{userId}

14、获取用户某个关注 GET
 "follow" : "541bf4de9fa52afb78246a26"
http://192.168.40.184:8080/citylife/api/v1/user/{followId}/follow