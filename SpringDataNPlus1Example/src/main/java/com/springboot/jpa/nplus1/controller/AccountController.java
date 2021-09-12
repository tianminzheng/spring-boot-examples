package com.springboot.jpa.nplus1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.jpa.nplus1.dto.AccountDTO;
import com.springboot.jpa.nplus1.service.AccountService;

@RestController
@RequestMapping(value = "/accounts")
public class AccountController {

	@Autowired
	private AccountService service;

	@GetMapping(value = "/original")
	public ResponseEntity<List<AccountDTO>> findOriginalAccount() {

		List<AccountDTO> list = service.findOriginalAccount();
		return ResponseEntity.ok(list);
	}
	
	@GetMapping(value = "/original/paged")
	public ResponseEntity<Page<AccountDTO>> findOriginalPagedAccount(@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "size", defaultValue = "10") Integer size) {

		PageRequest pageRequest = PageRequest.of(page, size);
		Page<AccountDTO> list = service.findOriginalPagedAccount(pageRequest);
		return ResponseEntity.ok(list);
	}

	@GetMapping(value = "/new")
	public ResponseEntity<List<AccountDTO>> findAccounts() {
		List<AccountDTO> list = service.findAccounts();
		return ResponseEntity.ok(list);
	}

	@GetMapping(value = "/new/paged")
	public ResponseEntity<Page<AccountDTO>> findPagedAccount(@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "size", defaultValue = "10") Integer size) {

		PageRequest pageRequest = PageRequest.of(page, size);
		Page<AccountDTO> list = service.findPagedAccount(pageRequest);

		return ResponseEntity.ok(list);
	}
}
