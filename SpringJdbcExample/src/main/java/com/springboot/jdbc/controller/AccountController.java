package com.springboot.jdbc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.jdbc.domain.Account;
import com.springboot.jdbc.service.AccountService;


@RestController
@RequestMapping(value="accounts")
public class AccountController {
  
	@Autowired
	AccountService accountService;
    	
	@GetMapping(value = "/{accountId}")
    public Account getAccountById(@PathVariable Long accountId) {	
		
		Account account = accountService.getAccountById(accountId);
    	return account;
    }
	
	@GetMapping(value = "accountNumber/{accountNumber}")
    public Account getAccountByAccountNumber(@PathVariable String accountNumber) {	
		
		Account account = accountService.getAccountDetailByAccountNumber(accountNumber);
    	return account;
    }
	
	@PostMapping(value = "")
    public Account addAccount(@RequestBody Account account) {	
		
		Account result = accountService.addAccount(account);
    	return result;
    }
}
