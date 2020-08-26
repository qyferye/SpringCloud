package com.cloud.mybatis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @ Author     ：cloud
 * @ Date       ：Created in  2020-08-21 9:03
 * @ Description：SpringCloud
 */
@SpringBootApplication
@MapperScan("com.cloud.mybatis.mapper")
public class MybatisApplication {
    public static void main(String[] args) {
        SpringApplication.run(MybatisApplication.class, args);
        System.out.println("项目启动!");
    }
}
