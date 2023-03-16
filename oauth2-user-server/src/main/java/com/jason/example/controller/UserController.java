package com.jason.example.controller;

import com.jason.example.common.CommonResult;
import com.jason.example.common.enums.EnumSex;
import com.jason.example.domain.User;
import com.jason.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/list")
    public Object getList(){
        return CommonResult.success(userService.getLists());
    }

    @PostMapping("/save")
    public Object save(@RequestBody User user){
        return CommonResult.success(userService.save(user));
    }

    @PostMapping("/getEnum")
    public Object getEnum(EnumSex sex){
        return CommonResult.success(sex);
    }

    @PostMapping("/getVip")
    public Object getEnum(User sex){
        Logger.getGlobal().info(sex.toString());
        return CommonResult.success(sex);
    }
}
