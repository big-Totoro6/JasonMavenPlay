启动项目:
启动nacos: startup.cmd -m standalone
docker 启动redis。
依次启动micro-oauth2-auth、micro-oauth2-gateway及micro-oauth2-api服务
使用密码模式获取JWT令牌，访问地址：http://localhost:9201/auth/oauth/token
使用获取到的JWT令牌访问需要权限的接口，访问地址：http://localhost:9201/api/hello
使用获取到的JWT令牌访问获取当前登录用户信息的接口，访问地址：http://localhost:9201/api/user/currentUser
当JWT令牌过期时，使用refresh_token获取新的JWT令牌，访问地址：http://localhost:9201/auth/oauth/token