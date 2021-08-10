package com.springboot.aop.proxy.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;

import com.springboot.aop.proxy.service.AccountService;
import com.springboot.aop.proxy.service.Impl.AccountServiceImpl;


@Configuration
@EnableAspectJAutoProxy
public class JDKProxyAppConfig {

	@Bean
	@Scope(proxyMode=ScopedProxyMode.INTERFACES)
	public AccountService accountService(){
		return new AccountServiceImpl();
	}
}
