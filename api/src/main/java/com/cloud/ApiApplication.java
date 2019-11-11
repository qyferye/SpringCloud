package com.cloud;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@Slf4j
@MapperScan("com.cloud.dao.mapper")
public class ApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(ApiApplication.class, args);
        System.out.println("项目启动!");
    }

   /* @Bean
    public CommandLineRunner test(SingletonBean bean) {
        return (args)-> {
            log.info("测试单例bean和原型bean的调用");
            int i =0;
            while(i<3) {
                i++;
                bean.print();
            }
        };
    }*/
}
