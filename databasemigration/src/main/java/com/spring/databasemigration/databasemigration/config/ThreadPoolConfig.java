package com.spring.databasemigration.databasemigration.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
public class ThreadPoolConfig {

	@Bean
	public ThreadPoolTaskExecutor threadPoolTaskExecutor()
	{
		ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
		threadPoolTaskExecutor.setMaxPoolSize(10);
		threadPoolTaskExecutor.setCorePoolSize(10);
		threadPoolTaskExecutor.initialize();
		return threadPoolTaskExecutor;
	}
}
