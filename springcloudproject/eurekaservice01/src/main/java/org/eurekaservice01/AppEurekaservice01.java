package org.eurekaservice01;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@EnableEurekaServer
public class AppEurekaservice01 
{
    public static void main( String[] args )
    {
        SpringApplication.run(AppEurekaservice01.class, args);
    }
}
