package com.kolosensei.springboottooltemplate;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author zhengyang
 * @version 1.0
 * @date 2020/11/25 16:19
 * @description
 */
@SpringBootApplication
@EnableFeignClients
@MapperScan(basePackages = "com.kolosensei.springboottooltemplate.mapper")
public class SpringBootToolTemplateApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootToolTemplateApplication.class, args);
    }
}
