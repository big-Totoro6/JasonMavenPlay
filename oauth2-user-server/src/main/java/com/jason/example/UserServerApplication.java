package com.jason.example;

import com.alibaba.nacos.common.JustForTest;
import com.jason.example.domain.User;
import jakarta.persistence.Entity;
import org.junit.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan(basePackages = "com/jason/example/domain")
@SpringBootApplication
public class UserServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserServerApplication.class);
    }
}
