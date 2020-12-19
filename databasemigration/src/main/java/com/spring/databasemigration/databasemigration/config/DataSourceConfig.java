package com.spring.databasemigration.databasemigration.config;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;

import com.spring.databasemigration.databasemigration.event.DataSourceEvent;
import com.spring.databasemigration.databasemigration.listener.DataSourceListener;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
public class DataSourceConfig {

	/**
	 * @Description: 通过配置使用@Profile属性来进行激活那个 就不需要数据库路由
	 * @date: 2020年12月19日 下午12:09:45
	 * @return
	 */
	@Bean
	@ConfigurationProperties(prefix = "mysql.spring.datasource")
	public DataSourceProperties mysqlDataSourceProperties() {
		return new DataSourceProperties();
	}
	
	@Bean
	@ConfigurationProperties(prefix = "mysql.spring.datasource")
	public DataSource mysqlDataSource() {
		return mysqlDataSourceProperties().initializeDataSourceBuilder().type(HikariDataSource.class).build();
	}

	@Primary
	@Bean
	@ConfigurationProperties(prefix = "oracle.spring.datasource")
	public DataSourceProperties oracleDataSourceProperties() {
		return new DataSourceProperties();
	}
	
	@Bean
	@ConfigurationProperties(prefix = "oracle.spring.datasource")
	public DataSource oracleDataSource() {
		return oracleDataSourceProperties().initializeDataSourceBuilder().type(HikariDataSource.class).build();
	}

    @Primary//不加这个会报错。
    @DependsOn({ "mysqlDataSource", "oracleDataSource"}) //解决数据库循环依赖问题 主要确保需要依赖的bean全部初始化了
    @Bean(name = "multiDataSource")
    public MultiRouteDataSource exampleRouteDataSource() {
        MultiRouteDataSource multiDataSource = new MultiRouteDataSource();
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put("mysqlDataSource", mysqlDataSource());
        targetDataSources.put("oracleDataSource", oracleDataSource());
        multiDataSource.setTargetDataSources(targetDataSources);
        multiDataSource.setDefaultTargetDataSource(oracleDataSource());
        return multiDataSource;
    }

    ///////////////////////事件学习///////////////////////////////
    @Bean
    public DataSourceListener dataSourceListener()
    {
    	return new DataSourceListener();
    }
    
}
