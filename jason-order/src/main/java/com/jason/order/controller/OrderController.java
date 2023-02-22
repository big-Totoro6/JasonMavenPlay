package com.jason.order.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.logging.Logger;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    RestTemplate restTemplate;

    @RequestMapping("/add")
    public String add() {
        Logger.getGlobal().info("下单成功");
        String msg = restTemplate.getForObject("http://localhost:9505/stock/reduct", String.class);
        return "下单成功";
    }
}
