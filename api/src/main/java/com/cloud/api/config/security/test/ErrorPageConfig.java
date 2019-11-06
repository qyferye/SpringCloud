package com.cloud.api.config.security.test;

import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;

/*

在Spring Boot2.0以上配置嵌入式Servlet容器时EmbeddedServletContainerCustomizer类不存在，经网络查询发现被WebServerFactoryCustomizer替代

* springboot1.x的相关类如下：

    org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer
    org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer
    org.springframework.boot.context.embedded.tomcat.TomcatConnectorCustomizer
    org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory

springboot2.x的相关类如下：

    org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory
    org.springframework.boot.web.server.WebServerFactoryCustomizer
    org.springframework.boot.web.embedded.tomcat.TomcatConnectorCustomizer
    org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory

    对比一下可以清楚看到相关的改动
* */


//@Configuration
public class ErrorPageConfig {
    @Bean
    public WebServerFactoryCustomizer<ConfigurableWebServerFactory> webServerFactoryCustomizer(){
        return new WebServerFactoryCustomizer<ConfigurableWebServerFactory>() {
            @Override
            public void customize(ConfigurableWebServerFactory factory) {
                factory.addErrorPages(new ErrorPage(HttpStatus.FORBIDDEN,"/errorPage"));
            }
        };
    }
}
