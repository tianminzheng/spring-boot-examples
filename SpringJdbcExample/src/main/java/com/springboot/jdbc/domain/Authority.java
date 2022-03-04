package com.springboot.jdbc.domain;

public class Authority {
	private Long id;	
	private String authorityCode;
	private String authorityName;
	private String description;
	
	public Authority() {
		super();
	}

	public Authority(Long id, String authorityCode, String authorityName, String description) {
		super();
		this.id = id;
		this.authorityCode = authorityCode;
		this.authorityName = authorityName;
		this.description = description;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAuthorityCode() {
		return authorityCode;
	}

	public void setAuthorityCode(String authorityCode) {
		this.authorityCode = authorityCode;
	}

	public String getAuthorityName() {
		return authorityName;
	}

	public void setAuthorityName(String authorityName) {
		this.authorityName = authorityName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}	
}
