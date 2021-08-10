package com.springboot.aop.proxy.service.Impl;

import com.springboot.aop.proxy.model.Account;
import com.springboot.aop.proxy.service.AccountService;

public class AccountServiceImpl implements AccountService {
	@Override
	public boolean doAccountTransaction(Account source, Account dest, int amount) {
		
		return true;
	}

}
