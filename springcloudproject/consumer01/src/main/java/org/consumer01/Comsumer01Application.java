package org.consumer01;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class Comsumer01Application {
	public static void main(String[] args) {
		SpringApplication.run(Comsumer01Application.class, args);
	}
}
