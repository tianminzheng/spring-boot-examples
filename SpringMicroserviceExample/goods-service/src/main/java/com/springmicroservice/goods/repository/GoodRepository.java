package com.springmicroservice.goods.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.springmicroservice.goods.domain.Good;

@Repository
public interface GoodRepository extends MongoRepository<Good, String> {
	
	Good findGoodByCode(String code);

	Good findGoodById(String id);
}
