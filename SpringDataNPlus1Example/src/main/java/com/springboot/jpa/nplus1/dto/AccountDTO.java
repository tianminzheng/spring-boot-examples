package com.springboot.jpa.nplus1.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.springboot.jpa.nplus1.domain.Account;

public class AccountDTO {

	private Long id;
	private String name;
	
	private List<AuthorityDTO> authorities = new ArrayList<>();
	
	public AccountDTO() {
	}

	public AccountDTO(Long id, String name, List<AuthorityDTO> authorities) {
		super();
		this.id = id;
		this.name = name;
		this.authorities = authorities;
	}
	
	public AccountDTO(Account product) {
		id = product.getId();
		name = product.getName();
		authorities = product.getAuthorities().stream().map(x -> new AuthorityDTO(x)).collect(Collectors.toList());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<AuthorityDTO> getCategories() {
		return authorities;
	}
}
