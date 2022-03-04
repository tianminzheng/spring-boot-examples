package com.springmicroservice.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springmicroservice.order.domain.Order;
import com.springmicroservice.order.service.OrderService;

@RestController
@RequestMapping(value = "orders")
public class OrderController {

	@Autowired
	OrderService orderService;

	@GetMapping(value = "/{userId}/{goodsCode}")
	public Order addOrder(@PathVariable("userId") String userId,
			@PathVariable("goodsCode") String goodsCode) {

		return orderService.generateOrder(userId, goodsCode);
	}
}
