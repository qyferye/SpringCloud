package com.cloud;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @ Description   :  SpringBootApplication
 * @ Author        :  秦云峰（cloud）
 * @ CreateDate    :  2020-03-26 15:19
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@Slf4j
@EnableAspectJAutoProxy(exposeProxy=true)
@EnableTransactionManagement
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        System.out.println("项目启动!");
    }
    /*
    * 在完成了引入AOP依赖包后，一般来说并不需要去做其他配置。
    * 也许在Spring中使用过注解配置方式的人会问是否需要在程序主类中增加@EnableAspectJAutoProxy来启用，实际并不需要。
       可以看关于AOP的默认配置属性，其中spring.aop.auto属性默认是开启的，也就是说只要引入了AOP依赖后，
       * 默认已经增加了@EnableAspectJAutoProxy。
    * */
}
