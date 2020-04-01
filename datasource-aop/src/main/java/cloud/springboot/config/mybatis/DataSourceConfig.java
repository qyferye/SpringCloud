package cloud.springboot.config.mybatis;

import cloud.springboot.enums.DBTypeEnum;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DataSourceConfig {
 
    @Autowired
    Environment environment;
 
    @Bean
    @ConfigurationProperties("spring.datasource.test1")
    public DataSource readDataSourceTest1() {
        DataSource build = DataSourceBuilder.create().build();
        HikariDataSource hikariDataSource = buildDataSource(build,"test1");
        return hikariDataSource;
    }
 
    @Bean
    @ConfigurationProperties("spring.datasource.test2")
    public DataSource readDataSourceTest2() {
        DataSource build = DataSourceBuilder.create().build();
        HikariDataSource hikariDataSource = buildDataSource(build,"test2");
        return hikariDataSource;
    }
 
    @Bean
    public DataSource myRoutingDataSource(@Qualifier("readDataSourceTest1") DataSource readDataSourceTest1,
                                          @Qualifier("readDataSourceTest2") DataSource readDataSourceTest2
                                           ) {
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put(DBTypeEnum.READ_TEST1, readDataSourceTest1);
        targetDataSources.put(DBTypeEnum.READ_TEST2, readDataSourceTest2);
        MyRoutingDataSource myRoutingDataSource = new MyRoutingDataSource();
        myRoutingDataSource.setDefaultTargetDataSource(readDataSourceTest1);
        myRoutingDataSource.setTargetDataSources(targetDataSources);
        return myRoutingDataSource;
    }
 
    public HikariDataSource buildDataSource(DataSource dataSource,String dataSourcePrefix){
        HikariDataSource hikariDataSource= (HikariDataSource) dataSource;
        hikariDataSource.setDriverClassName(environment.getProperty("spring.datasource."+dataSourcePrefix+".driver-class-name"));
        hikariDataSource.setJdbcUrl(environment.getProperty("spring.datasource."+dataSourcePrefix+".jdbc-url"));
        hikariDataSource.setUsername(environment.getProperty("spring.datasource."+dataSourcePrefix+".username"));
        hikariDataSource.setPassword(environment.getProperty("spring.datasource."+dataSourcePrefix+".password"));
        hikariDataSource.setMinimumIdle(Integer.parseInt(environment.getProperty("spring.datasource."+dataSourcePrefix+".minIdle")));
        hikariDataSource.setConnectionTimeout(Long.parseLong(environment.getProperty("spring.datasource."+dataSourcePrefix+".connectionTimeout")));
        hikariDataSource.setValidationTimeout(Long.parseLong(environment.getProperty("spring.datasource."+dataSourcePrefix+".validationTimeout")));
        hikariDataSource.setMaximumPoolSize(Integer.parseInt(environment.getProperty("spring.datasource."+dataSourcePrefix+".maxPoolSize")));
        return hikariDataSource;
    }
}