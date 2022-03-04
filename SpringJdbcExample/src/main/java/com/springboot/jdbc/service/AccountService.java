package com.springboot.jdbc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.springboot.jdbc.domain.Account;
import com.springboot.jdbc.repository.AccountRepository;


@Service
public class AccountService {

	@Autowired
    @Qualifier("accountJdbcRepository")
	private AccountRepository accountRepository;
	
	
	public Account getAccountById(Long accountId) {
		
		return accountRepository.getAccountById(accountId);
	}
	
	public Account getAccountDetailByAccountNumber(String accountNumber) {
		return accountRepository.getAuthoritiesByAccountNumber(accountNumber);
	}
	
	public Account addAccount(Account account) {
		return accountRepository.addAccount(account);
	}
	
}

