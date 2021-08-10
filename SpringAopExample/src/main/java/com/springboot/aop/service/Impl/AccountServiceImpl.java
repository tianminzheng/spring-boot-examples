package com.springboot.aop.service.Impl;

import org.apache.log4j.Logger;

import com.springboot.aop.exception.MinimumAmountException;
import com.springboot.aop.model.Account;
import com.springboot.aop.service.AccountService;

public class AccountServiceImpl implements AccountService {
	private static final Logger LOGGER = Logger.getLogger(AccountServiceImpl.class);

	@Override
	public boolean doAccountTransaction(Account source, Account dest, int amount) throws MinimumAmountException {
		LOGGER.info("执行交易");
		
		if (amount < 10) {
			throw new MinimumAmountException("交易金额过少");
		}
		return true;
	}

}
