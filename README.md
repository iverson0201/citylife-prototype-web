1、rest架构
2、逻辑控制 spring4 mvc
3、自定义Exception类型，并返回客户端友好的错误信息
4、用户认证与权限管理暂使用Spring Security，后面准备换成Apache Shiro
5、数据存储使用MongoDB
6、数据通信使用jackson，返回的json字段可以根据业务需要动态控制
7、领域层 domain
8、表示层RESTful，Url设计原则：
   以资源+参数的形式设计，如：
   https://api.xujw.com/user		Http/GET	
   返回所有用户
9、全文检索现在是elasticsearch的天下了
10、云存储目前使用第三方的七牛云存储
11、云通讯使用Openfire 