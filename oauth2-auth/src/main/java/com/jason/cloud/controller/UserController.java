package com.jason.cloud.controller;

import cn.dev33.satoken.stp.StpUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

@RestController
@RequestMapping("/user/")
public class UserController {
    public Logger global = Logger.getGlobal();

    // 测试登录，浏览器访问： http://localhost:9601/user/doLogin?username=zhang&password=123456
    @RequestMapping("doLogin")
    public String doLogin(String username, String password) {
        // 此处仅作模拟示例，真实项目需要从数据库中查询数据进行比对
        if ("zhang".equals(username) && "123456".equals(password)) {
            StpUtil.login(10001);
            return "登录成功";
        }
        return "登录失败";
    }

    // 查询登录状态，浏览器访问： http://localhost:9601/user/isLogin
    @RequestMapping("isLogin")
    public String isLogin() {

        // 获取当前会话的token值
        String tokenValue = StpUtil.getTokenValue();
        global.info(tokenValue);
// 获取当前`StpLogic`的token名称

        global.info(StpUtil.getTokenName());
// 获取指定token对应的账号id，如果未登录，则返回 null

        global.info((String) StpUtil.getLoginIdByToken(tokenValue));
// 获取当前会话剩余有效期（单位：s，返回-1代表永久有效）

        global.info(String.valueOf(StpUtil.getTokenTimeout()));
// 获取当前会话的token信息参数
        global.info(StpUtil.getTokenInfo().toString());
        return "当前会话是否登录：" + StpUtil.isLogin();
    }

}
