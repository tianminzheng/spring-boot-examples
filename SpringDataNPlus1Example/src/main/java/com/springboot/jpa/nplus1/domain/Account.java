package com.springboot.jpa.nplus1.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "account")
public class Account {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	
	@ManyToMany
	@JoinTable(name = "account_authority",
		joinColumns = @JoinColumn(name = "account_id"),
		inverseJoinColumns = @JoinColumn(name = "authority_id"))	
	Set<Authority> authorities = new HashSet<>();
	
	public Account() {
	}
	
	public Account(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
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

	public Set<Authority> getAuthorities() {
		return authorities;
	}
}
