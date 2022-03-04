package com.springmicroservice.order.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GoodRestTemplateClient {

    @Autowired
    RestTemplate restTemplate;

    public GoodMapper getGoodsByGoodsCode(String goodsCode) {
        ResponseEntity<GoodMapper> result =
                restTemplate.exchange("http://goodsservice/goods/{goodsCode}", HttpMethod.GET, null,
                		GoodMapper.class, goodsCode);

        return result.getBody();
    }
}
