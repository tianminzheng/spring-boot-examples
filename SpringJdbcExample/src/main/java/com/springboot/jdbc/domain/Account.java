package com.springboot.jdbc.domain;

import java.util.ArrayList;
import java.util.List;

public class Account {

	private Long id;
	private String accountNumber;
	private String accountName;
	private List<Authority> authorities;

	public Account() {
		super();
	}

	public Account(Long id, String accountNumber, String accountName, List<Authority> authorities) {
		super();
		this.id = id;
		this.accountNumber = accountNumber;
		this.accountName = accountName;
		this.authorities = authorities;
	}

	public Account(Long id, String accountNumber, String accountName) {
		super();
		this.id = id;
		this.accountNumber = accountNumber;
		this.accountName = accountName;
		this.authorities = new ArrayList<Authority>();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public List<Authority> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(List<Authority> authorities) {
		this.authorities = authorities;
	}

	public void addAuthority(Authority authority) {
		authorities.add(authority);
	}

}
