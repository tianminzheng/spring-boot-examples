package com.springboot.webflux.order.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.springboot.webflux.order.domain.Order;

@Repository
public interface OrderRepository extends ReactiveMongoRepository<Order, Long> {

}
