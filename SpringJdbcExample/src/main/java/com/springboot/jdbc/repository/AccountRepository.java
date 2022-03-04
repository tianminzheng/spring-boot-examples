package com.springboot.jdbc.repository;

import com.springboot.jdbc.domain.Account;

public interface AccountRepository {
	
	Account addAccount(Account Account);
	
	Account getAccountById(Long AccountId);
	
	Account getAuthoritiesByAccountNumber(String accountNumber);
}
