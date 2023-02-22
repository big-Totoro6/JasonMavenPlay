package com.jason.example;

import com.alibaba.nacos.common.JustForTest;
import com.jason.example.domain.User;
import org.junit.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UserServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserServerApplication.class);
    }
}
