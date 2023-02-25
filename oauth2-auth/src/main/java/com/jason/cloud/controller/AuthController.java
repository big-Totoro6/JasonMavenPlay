package com.jason.cloud.controller;

import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 权限测试
 */
@RestController
@RequestMapping("/auth/")
public class AuthController {
    // 查询 permission 信息  ---- http://localhost:9601/auth/permission
    @RequestMapping("permission")
    public SaResult permission() {
        // 获取：当前账号所拥有的权限集合
        List<String> permissionList = StpUtil.getPermissionList();

        // 判断：当前账号是否含有指定权限, 返回 true 或 false
        boolean b = StpUtil.hasPermission("user.add");

        // 校验：当前账号是否含有指定权限, 如果验证未通过，则抛出异常: NotPermissionException
        StpUtil.checkPermission("user.add");

        // 校验：当前账号是否含有指定权限 [指定多个，必须全部验证通过]
        StpUtil.checkPermissionAnd("user.add", "user.delete", "user.get");

        // 校验：当前账号是否含有指定权限 [指定多个，只要其一验证通过即可]
        StpUtil.checkPermissionOr("user.add", "user.delete", "user.get");

        return SaResult.data(StpUtil.getTokenInfo());
    }

    // 查询 permission 信息  ---- http://localhost:9601/auth/rule
    @RequestMapping("rule")
    public SaResult rule() {
        // 获取：当前账号所拥有的角色集合
        StpUtil.getRoleList();

        // 判断：当前账号是否拥有指定角色, 返回 true 或 false
        StpUtil.hasRole("super-admin");

        // 校验：当前账号是否含有指定角色标识, 如果验证未通过，则抛出异常: NotRoleException
        StpUtil.checkRole("super-admin");

        // 校验：当前账号是否含有指定角色标识 [指定多个，必须全部验证通过]
        StpUtil.checkRoleAnd("super-admin", "shop-admin");

        // 校验：当前账号是否含有指定角色标识 [指定多个，只要其一验证通过即可]
        StpUtil.checkRoleOr("super-admin", "shop-admin");


        return SaResult.data(StpUtil.getTokenInfo());
    }
}
