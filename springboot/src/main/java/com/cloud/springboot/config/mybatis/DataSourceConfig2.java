package com.cloud.springboot.config.mybatis;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = "com.cloud.springboot.dao.mapper.mapper2", sqlSessionFactoryRef = "test2SqlSessionFactory")
public class DataSourceConfig2 {
    @Bean(name = "test2DataSource")
    @ConfigurationProperties(prefix = "spring.datasource.test2")
    public DataSource getDateSource2()
    {
        return DataSourceBuilder.create().build();
    }
 
    @Bean(name = "test2SqlSessionFactory")
    public SqlSessionFactory test2SqlSessionFactory(@Qualifier("test2DataSource") DataSource datasource)
            throws Exception
    {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(datasource);
        bean.setMapperLocations(
                new PathMatchingResourcePatternResolver().getResources("classpath:mapper/datasource2/*.xml"));
        return bean.getObject();
    }
 
    @Bean("test2SqlSessionTemplate")
    public SqlSessionTemplate test2SqlSessionTemplate(
            @Qualifier("test2SqlSessionFactory") SqlSessionFactory sessionFactory)
    {
        return new SqlSessionTemplate(sessionFactory);
    }
    /******配置事务管理********/
    @Bean
    public PlatformTransactionManager test2TransactionManager(@Qualifier("test2DataSource")DataSource test2DataSource) {
        return new DataSourceTransactionManager(test2DataSource);
    }
}