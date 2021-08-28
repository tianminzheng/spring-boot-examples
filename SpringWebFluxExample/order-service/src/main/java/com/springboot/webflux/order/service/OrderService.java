package com.springboot.webflux.order.service;

import com.springboot.webflux.order.client.UserMapper;
import com.springboot.webflux.order.client.UserWebClient;
import com.springboot.webflux.order.domain.Order;
import com.springboot.webflux.order.repository.OrderRepository;

import reactor.core.publisher.Mono;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
public class OrderService {

    @Autowired
    UserWebClient userWebClient;

    @Autowired
    OrderRepository orderRepository;

    public Mono<UserMapper> getUser(String userId) {

        return userWebClient.getUserById(userId);
    }

    public Mono<Order> generateOrder(String userId, String goodName) {
        Order order = new Order();

        //获取远程User信息
        Mono<UserMapper> user = getUser(userId);
        
        //验证目标用户是否存在
        if(user == null ) {
			return Mono.just(order);
		}
        
        //生成有效Order
        Mono<Order> monoOrder = user.flatMap(u -> {
        	order.setId(UUID.randomUUID().toString());
	        order.setUserId(userId);
			order.setOrderNumber("OrderNumber");
	        order.setDeliveryAddress("DemoDeliveryAddress");
	        order.setGoodsName(goodName);
	        order.setCreateTime(new Date());
			
			return Mono.just(order);
        });
        
        //保存Order
        return monoOrder.flatMap(orderRepository::save);    	
    }
}
