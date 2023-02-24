package com.jason.cloud.controller;

import com.jason.cloud.api.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    @GetMapping("/auth")
    public CommonResult<String> test(){
        return CommonResult.success("试试");
    }

    @GetMapping("/adminAuth")
    public CommonResult<String> adminAuth(){
        return CommonResult.success("adminAuth");
    }

    @GetMapping("/testAuth")
    public CommonResult<String> testAuth(){
        return CommonResult.success("testAuth");
    }

    @GetMapping("/product/{id}")
    public String getProduct(@PathVariable String id) {
        //for debug
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return "product id : " + id;
    }

    @GetMapping("/order/{id}")
    public String getOrder(@PathVariable String id) {
        //for debug
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return "order id : " + id;
    }

    @GetMapping("/ignore/getIgnore")
    public String getIgnore() {
        //for debug
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return "ignore";
    }
}
