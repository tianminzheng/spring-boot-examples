package com.springboot.aop.proxy.service;

import com.springboot.aop.proxy.model.Account;

public interface AccountService {
	
	boolean doAccountTransaction(Account source, Account dest, int amount);
}
