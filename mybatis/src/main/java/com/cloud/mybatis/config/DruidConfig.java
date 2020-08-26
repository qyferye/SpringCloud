package com.cloud.mybatis.config;

import com.alibaba.druid.pool.DruidDataSource;

import javax.sql.DataSource;

//@Configuration
public class DruidConfig {
   // @Bean
    //@ConfigurationProperties(prefix = "spring.datasource")//自动装配
    public DataSource getDataSource(){
        return new DruidDataSource();
    }
}