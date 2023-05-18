package com.jason.example.controller;

import com.jason.diyjson.service.MyDiyJsonService;
import com.jason.example.common.CommonResult;
import com.jason.example.common.enums.EnumSex;
import com.jason.example.domain.Customer;
import com.jason.example.domain.User;
import com.jason.example.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    private MyDiyJsonService myDiyJsonService;
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

    @GetMapping("getDiyCustomer")
    public Object getDiyCustomer(){
        log.info("getDiyCustomer");
        Customer build = Customer.builder().lastName("Z").firstName("Jason").email("wao@163.com").id(1).build();
        return myDiyJsonService.objectToMyJson(build);
    }
}
