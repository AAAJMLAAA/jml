package com.spring.demo.demoStart.config;

import java.io.IOException;

import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.FileSystemResource;

import com.spring.demo.demoStart.constart.ConfigConstart;

@Configuration
public class PropertiesConfig {
	@Bean
	public static PropertySourcesPlaceholderConfigurer properties() throws IOException {
		PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();
		PropertiesFactoryBean properties = new PropertiesFactoryBean();
		properties.setLocation(new FileSystemResource(ConfigConstart.CONFIG_PATH));
		System.out.println(ConfigConstart.CONFIG_PATH+"================");
		properties.afterPropertiesSet();
		configurer.setProperties(properties.getObject());
		return configurer;
	}
}
