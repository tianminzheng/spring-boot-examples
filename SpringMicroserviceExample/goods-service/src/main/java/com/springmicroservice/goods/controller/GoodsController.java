package com.springmicroservice.goods.controller;

import com.springmicroservice.goods.domain.Good;
import com.springmicroservice.goods.services.GoodsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/goods")
public class GoodsController {
 
	@Autowired
	GoodsService goodsService;

	@RequestMapping(value = "/{goodsCode}", method = RequestMethod.GET)
    public Good findGoodsByCode(@PathVariable String goodsCode) {

		Good goods = goodsService.findGoodsByCode(goodsCode);
    	return goods;
    }
	
	@RequestMapping(value = "/id/{goodsId}", method = RequestMethod.GET)
    public Good findGoodsById(@PathVariable String goodsId) {

		Good goods = goodsService.findGoodsById(goodsId);
    	return goods;
    }
}
