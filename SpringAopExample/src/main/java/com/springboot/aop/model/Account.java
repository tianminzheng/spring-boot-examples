package com.springboot.aop.model;

public class Account {
	private String accountName;
	private Integer accountNumber;
	
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	
	public Integer getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(Integer accountNumber) {
		this.accountNumber = accountNumber;
	}

	public Account(Integer accountNumber, String accountName) {
		this.accountNumber = accountNumber;
		this.accountName = accountName;
	}
}
