package com.springboot.async.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
public class MyExecutor {

	@Bean
	public static ThreadPoolTaskExecutor getExecutor() {
		ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
		taskExecutor.setCorePoolSize(30);
		taskExecutor.setMaxPoolSize(30);
		taskExecutor.setQueueCapacity(50);
		taskExecutor.setThreadNamePrefix("Web");
		return taskExecutor;
	}
}
