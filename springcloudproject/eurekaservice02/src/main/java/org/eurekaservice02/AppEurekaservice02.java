package org.eurekaservice02;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@EnableEurekaServer
public class AppEurekaservice02 
{
    public static void main( String[] args )
    {
        SpringApplication.run(AppEurekaservice02.class, args);
    }
}
