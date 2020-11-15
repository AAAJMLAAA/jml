package spring_message_comsumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;

@SpringBootApplication
@EnableJms 
public class ComsunerApplication {
	public static void main(String[] args) {
		SpringApplication.run(ComsunerApplication.class, args);
	}
}

