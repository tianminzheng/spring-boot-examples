package com.springboot.jpa.nplus1.dto;

import com.springboot.jpa.nplus1.domain.Authority;

public class AuthorityDTO {

	private Long id;
	private String name;
	
	public AuthorityDTO() {
	}

	public AuthorityDTO(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	public AuthorityDTO(Authority category) {
		id = category.getId();
		name = category.getName();
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
}
