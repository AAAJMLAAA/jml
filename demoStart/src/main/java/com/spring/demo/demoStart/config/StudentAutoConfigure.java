package com.spring.demo.demoStart.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages={"com.spring"})
//@EnableConfigurationProperties(value = DemoProperties.class)
public class StudentAutoConfigure {

	/**
	 * 这是一种方式，但是这种太low比较麻烦
	 * */
//	@Bean
//	public StudentService studentService() {
//		return new StudentServiceImpl();
//	}
}
