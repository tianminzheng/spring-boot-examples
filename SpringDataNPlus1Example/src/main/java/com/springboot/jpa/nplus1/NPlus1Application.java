package com.springboot.jpa.nplus1;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.springboot.jpa.nplus1.domain.Authority;
import com.springboot.jpa.nplus1.domain.Account;
import com.springboot.jpa.nplus1.repository.AuthorityRepository;
import com.springboot.jpa.nplus1.repository.AccountRepository;

@SpringBootApplication
public class NPlus1Application implements CommandLineRunner {

	@Autowired
	private AuthorityRepository authorityRepository;
	
	@Autowired
	private AccountRepository accountRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(NPlus1Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Authority authority1 = new Authority(null, "READ");
		Authority authority2 = new Authority(null, "WRITE");
		Authority authority3 = new Authority(null, "READ-WRITE");

		Account account1 = new Account(null, "A1");
		Account account2 = new Account(null, "A2");
		Account account3 = new Account(null, "A3");
		Account account4 = new Account(null, "A4");
		Account account5 = new Account(null, "A5");
		Account account6 = new Account(null, "A6");
		Account account7 = new Account(null, "A7");
		Account account8 = new Account(null, "A8");
		Account account9 = new Account(null, "A9");
		Account account10 = new Account(null, "A10");

		account1.getAuthorities().addAll(Arrays.asList(authority1, authority2));
		account2.getAuthorities().addAll(Arrays.asList(authority1, authority3));
		account3.getAuthorities().addAll(Arrays.asList(authority2, authority3));
		account4.getAuthorities().addAll(Arrays.asList(authority1, authority2));
		account5.getAuthorities().addAll(Arrays.asList(authority2, authority3));
		account6.getAuthorities().addAll(Arrays.asList(authority1, authority3));
		account7.getAuthorities().addAll(Arrays.asList(authority1));
		account8.getAuthorities().addAll(Arrays.asList(authority3));
		account9.getAuthorities().addAll(Arrays.asList(authority2, authority3));
		account10.getAuthorities().addAll(Arrays.asList(authority1, authority3));
		
		authorityRepository.saveAll(Arrays.asList(authority1, authority2, authority3));
		
		accountRepository.saveAll(Arrays.asList(account1, account2, account3, account4, account5, account6, account7, account8, account9, account10));
	}
}
