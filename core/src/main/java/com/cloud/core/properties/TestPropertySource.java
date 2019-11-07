package com.cloud.core.properties;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.io.Serializable;
@Data
@Component
@PropertySource("classpath:testSource.properties")
@ConfigurationProperties(prefix = "cloud" ,ignoreInvalidFields = true)
public class TestPropertySource implements Serializable {
    /**
    * 1、yml文件中定义的属性值可以直接在类中 使用@Value("${xx.xx}")取出，但是yml文件中必须有该值的配置
    *
    *2、@PropertySource("classpath:xx.properties")使用@Value("${xx.xx}")取出  直接将 xx.properties 文件中定义的值赋值给类的属性
    *     但是使用@Value注解方式有一个不太友好的地方就是，当项目中有大量的属性进行配置的时候，我们需要一个个的在类的字段中增加@Value注解，这样确实很费劲，
    *     不过我们可以通过Springboot提供的@ConfigurationProperties注解解决这个问题。
    *
    * 3、@PropertySource("classpath:xx.properties")+@ConfigurationProperties("cloud")  直接将xx.properties文件中cloud前缀下的值赋值给对应属性，不需要使用@value
     *
     * 如果@value与 yml中的冲突   ， 将使用 yml文件中的
     *
     *
     * 注释；@PropertySource
     * encoding用于指定读取属性文件所使用的编码，我们通常使用的是UTF-8；ignoreResourceNotFound含义是当指定的配置文件不存在是否报错，默认是false;比如上文中指定的加载属性文件是jdbc-bainuo-dev.properties。
     * 如果该文件不存在，则ignoreResourceNotFound为true的时候，程序不会报错，如果ignoreResourceNotFound为false的时候，程序直接报错。实际项目开发中，最好设置ignoreResourceNotFound为false。该参数默认值为false。
     *
     *
     * 注释；@ConfigurationProperties
     * @ConfigurationProperties(prefix = "spring.datasource.shareniu",ignoreUnknownFields=true,ignoreInvalidFields=true)
     *
     * ignoreUnknownFields：忽略未知的字段。
     *
     * ignoreInvalidFields：是否忽略验证失败的字段。这个怎么理解呢？比如我们在配置文件中配置了一个字符串类型的变量，类中的字段是int类型，那肯定会报错的。如果出现这种情况我们可以容忍，则需要配置该属性值为true。
     * 该参数值默认为false。
     *
     *
     *
     *
     * 其实 @PropertySource  负责定义变量取值的来源   优先级低于  yml
     *     @Value   负责一个一个取值
     *     @ConfigurationProperties  批量取值
     *     但是@Value  优先级高于  @ConfigurationProperties
    *
    * **/


@Value("${cloud.lj}")
private String lj;

private String zzl;


private String ckl;




}
