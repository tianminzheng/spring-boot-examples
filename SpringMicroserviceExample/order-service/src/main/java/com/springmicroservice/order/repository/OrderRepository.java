package com.springmicroservice.order.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.springmicroservice.order.domain.Order;

@Repository
public interface OrderRepository extends MongoRepository<Order, String> {

}
