package com.springboot.aop.service;

import com.springboot.aop.exception.MinimumAmountException;
import com.springboot.aop.model.Account;

public interface AccountService {
	
	boolean doAccountTransaction(Account source, Account dest, int amount) throws MinimumAmountException;
}
