package com.jml.demo.demoStart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.spring.demo.demoStart.EnableStudentClient;

@SpringBootApplication
@EnableStudentClient
public class TestOtherApplication {
	public static void main(String[] args) {
		SpringApplication.run(TestOtherApplication.class, args);
	}
}
