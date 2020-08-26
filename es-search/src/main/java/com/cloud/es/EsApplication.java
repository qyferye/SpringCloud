package com.cloud.es;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EsApplication {
    public static void main(String[] args) {
        SpringApplication.run(EsApplication.class, args);
        System.out.println("项目启动!");
    }
}
