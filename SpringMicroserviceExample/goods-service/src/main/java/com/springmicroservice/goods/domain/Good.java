package com.springmicroservice.goods.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document("goods")
public class Good {	

    @Id
	private String id;
    @Field("code")
	private String code;
    @Field("name")
	private String name;
    @Field("price")
	private Double price;    
    
	public Good(String id, String code, String name, Double price) {
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