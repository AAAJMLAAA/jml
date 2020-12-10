package com.spring.demo.demoStart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {  // 或者实现ApplicationRun的接口
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
}
