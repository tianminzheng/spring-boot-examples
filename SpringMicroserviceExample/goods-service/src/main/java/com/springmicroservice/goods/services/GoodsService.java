package com.springmicroservice.goods.services;

import com.springmicroservice.goods.domain.Good;
import com.springmicroservice.goods.repository.GoodRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GoodsService {

	@Autowired
	private GoodRepository goodsRepository;
	
	public Good findGoodsByCode(String goodsCode) {
		
		return goodsRepository.findGoodByCode(goodsCode);
	}

	public Good findGoodsById(String goodsId) {
		
		return goodsRepository.findGoodById(goodsId);
	}
}

