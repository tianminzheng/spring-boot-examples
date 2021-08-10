package com.springboot.aop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import com.springboot.aop.aspect.AccountServiceAspect;
import com.springboot.aop.service.AccountService;
import com.springboot.aop.service.Impl.AccountServiceImpl;

@Configuration
@EnableAspectJAutoProxy
public class AppConfig {

	@Bean
	public AccountService transferService(){
		return new AccountServiceImpl();
	}
	
	@Bean
	public AccountServiceAspect transferServiceAspect(){
		return new AccountServiceAspect();
	}
}
