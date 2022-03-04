package com.springmicroservice.order.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

@Document("orders")
public class Order {
	@Id
    private String id;
	@Field("userId")
    private String userId;
	@Field("goodsName")
    private String goodsName;
	@Field("orderNumber")
    private String orderNumber;
	@Field("deliveryAddress")
	private String deliveryAddress;
	@Field("createTime")
	private Date createTime;
	
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}


	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getDeliveryAddress() {
		return deliveryAddress;
	}

	public void setDeliveryAddress(String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}
