package com.springboot.jpa.nplus1.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.jpa.nplus1.domain.Account;
import com.springboot.jpa.nplus1.dto.AccountDTO;
import com.springboot.jpa.nplus1.repository.AccountRepository;

@Service
public class AccountService {
	
	@Autowired
	private AccountRepository repository;

	@Transactional(readOnly = true)
	public List<AccountDTO> findOriginalAccount() {
		List<Account> list = repository.findAll();
		return list.stream().map(x -> new AccountDTO(x)).collect(Collectors.toList());
	}
	
	@Transactional(readOnly = true)
	public Page<AccountDTO> findOriginalPagedAccount(PageRequest pageRequest) {
		Page<Account> list = repository.findAll(pageRequest);
		return list.map(x -> new AccountDTO(x));
	}

	
	@Transactional(readOnly = true)
	public List<AccountDTO> findAccounts() {
		List<Account> list = repository.findAccounts();
		return list.stream().map(x -> new AccountDTO(x)).collect(Collectors.toList());
	}


	@Transactional(readOnly = true)
	public Page<AccountDTO> findPagedAccount(PageRequest pageRequest) {
		Page<Account> page = repository.findAll(pageRequest);
		repository.findAccounts(page.stream().collect(Collectors.toList()));
		
		return page.map(x -> new AccountDTO(x));
	}
}
