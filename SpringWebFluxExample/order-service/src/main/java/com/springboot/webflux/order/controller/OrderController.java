package com.springboot.webflux.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.webflux.order.domain.Order;
import com.springboot.webflux.order.service.OrderService;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "orders")
public class OrderController {

	@Autowired
	OrderService orderService;

	@PostMapping(value = "/{userId}/{goodName}")
	public Mono<Order> generateOrder(@PathVariable("userId") String userId,
			@PathVariable("goodName") String goodName) {

		return orderService.generateOrder(userId, goodName);
	}
}
