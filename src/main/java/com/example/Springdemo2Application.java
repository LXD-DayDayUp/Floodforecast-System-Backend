package com.example;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@MapperScan("com.example.dao")
@SpringBootApplication
//@EnableWebMvc//开启由json转换为对象数据
public class Springdemo2Application {
    public static void main(String[] args) {
        SpringApplication.run(Springdemo2Application.class, args);
    }

}
