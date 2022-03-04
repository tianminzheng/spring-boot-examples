package com.springmicroservice.order.client;

import java.io.Serializable;

public class GoodMapper implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String code;
	private String name;
	private Double price;    
    
	public GoodMapper() {
		
	}
			
	public GoodMapper(String id, String code, String name, Double price) {
		super();
		this.id = id;
		this.code = code;
		this.name = name;
		this.price = price;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}	
}