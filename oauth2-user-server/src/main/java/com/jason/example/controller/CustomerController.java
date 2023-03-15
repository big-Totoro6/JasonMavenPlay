package com.jason.example.controller;

import com.jason.example.domain.CustomerRegistrationRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("api/v1/customers/")
public record CustomerController(CustomerService customeerService) {
    @PostMapping("save")
    public void registerCustomer(@RequestBody CustomerRegistrationRequest customerRegistrationRequest){
        log.info("new customer registration {}",customerRegistrationRequest);
        customeerService.registerCustomer(customerRegistrationRequest);
    }

    @GetMapping("getCustomers")
    public Object getCustomers(){
        log.info("getCustomers");
        return customeerService.customerRepository().findAll();
    }
}
