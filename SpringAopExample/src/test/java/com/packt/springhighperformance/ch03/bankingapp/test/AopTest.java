package com.packt.springhighperformance.ch03.bankingapp.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.springboot.aop.config.AppConfig;
import com.springboot.aop.exception.MinimumAmountException;
import com.springboot.aop.model.Account;
import com.springboot.aop.service.AccountService;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=AppConfig.class)
public class AopTest {
	
	@Autowired
	private AccountService accountService;
	
	@Test
	public void transferAmount() throws MinimumAmountException{
		Account source = new Account(123456,"Account1");
		Account dest = new Account(987654,"Account2");
		accountService.doAccountTransaction(source, dest, 100);
	}
	
	@Test
	public void transferAmountException() throws MinimumAmountException{
		Account source = new Account(123456,"Account1");
		Account dest = new Account(987654,"Account2");
		accountService.doAccountTransaction(source, dest, 9);
	}
}
