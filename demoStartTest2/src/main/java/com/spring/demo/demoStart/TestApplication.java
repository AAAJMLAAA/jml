package com.spring.demo.demoStart;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

import com.spring.demo.demoStart.constart.ConfigConstart;


@SpringBootApplication
@EnableAsync
public class TestApplication {

	public static void main(String[] args) throws IOException {
		ConfigConstart.CONFIG_PATH = "E:/test/application.properties";	
		SpringApplication.run(TestApplication.class, args);
	}
}
