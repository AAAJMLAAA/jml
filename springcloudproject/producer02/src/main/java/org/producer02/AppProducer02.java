package org.producer02;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@EnableEurekaClient
public class AppProducer02 {
	public static void main(String[] args) {
		SpringApplication.run(AppProducer02.class, args);
	}
}
