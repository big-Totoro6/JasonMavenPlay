package com.jason.stock.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

@RestController
@RequestMapping("/stock")
public class StockController {

    @RequestMapping("/reduct")
    public String reduct(){
        Logger.getGlobal().info("库存减一");
        return "库存减一";
    }
}
