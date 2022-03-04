package com.springboot.jpa.service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.springboot.jpa.domain.Account;
import com.springboot.jpa.repository.AccountRepository;

@Service
public class AccountService {

	@Autowired
	private AccountRepository accountRepository;

	public Account getAccountById(Long accountId) {

		return accountRepository.getOne(accountId);
	}

	public Account getAccountByAccountNumber(String accountNumber) {

		return accountRepository.getAccountByAccountNumber(accountNumber);
	}

	public Account getAccountByAccountNumberWithQuery(String accountNumber) {

		return accountRepository.getAccountByAccountNumberWithQuery(accountNumber);
	}

	public Account getAccountByAccountNumberByExample(String accountNumber) {
		Account account = new Account();
		account.setAccountNumber(accountNumber);

		ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreCase()
				.withMatcher("accountNumber", GenericPropertyMatchers.exact()).withIncludeNullValues();

		Example<Account> example = Example.of(account, matcher);

		return accountRepository.findOne(example).orElse(new Account());
	}
	
	public Account getAccountByAccountNumberBySpecification(String accountNumber) {
		Account account = new Account();
		account.setAccountNumber(accountNumber);

		@SuppressWarnings("serial")
		Specification<Account> spec = new Specification<Account>() {
            @Override
            public Predicate toPredicate(Root<Account> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Path<Object> accountNumberPath = root.get("accountNumber");
              
                Predicate predicate = cb.equal(accountNumberPath, accountNumber);
                return predicate;
            }
        };

		return accountRepository.findOne(spec).orElse(new Account());		
	}

	public Account addAccount(Account account) {

		return accountRepository.save(account);
	}

}
