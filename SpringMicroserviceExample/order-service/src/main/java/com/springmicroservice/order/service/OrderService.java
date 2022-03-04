package com.springmicroservice.order.service;

import com.springmicroservice.order.client.GoodMapper;
import com.springmicroservice.order.client.GoodRestTemplateClient;
import com.springmicroservice.order.client.UserMapper;
import com.springmicroservice.order.client.UserRestTemplateClient;
import com.springmicroservice.order.domain.Order;
import com.springmicroservice.order.repository.OrderRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class OrderService {

    @Autowired
    UserRestTemplateClient userRestTemplateClient;

    @Autowired
    GoodRestTemplateClient goodsRestTemplateClient;

    @Autowired
    OrderRepository orderRepository;

    private GoodMapper getGoodsFromRemote(String goodsCode) {

        return goodsRestTemplateClient.getGoodsByGoodsCode(goodsCode);
    }

    private UserMapper getUserFromRemote(String userId) {

        return userRestTemplateClient.getUserById(userId);
    }

    public Order generateOrder(String userId, String goodsCode) {

        Order order = new Order();

        //获取远程Goods信息
        GoodMapper goods = getGoodsFromRemote(goodsCode);
        if (goods == null) {
            return order;
        }

        //获取远程User信息
        UserMapper user = getUserFromRemote(userId);
        if (user == null) {
            return order;
        }

        order.setOrderNumber("DemoOrderNumber");
        order.setDeliveryAddress("DemoDeliveryAddress");
        order.setUserId(userId);
        order.setGoodsName(goods.getName());
        order.setCreateTime(new Date());

        orderRepository.save(order);

        return order;
    }
}
