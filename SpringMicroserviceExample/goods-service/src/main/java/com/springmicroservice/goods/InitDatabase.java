
package com.springmicroservice.goods;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Component;

import com.springmicroservice.goods.domain.Good;


@Component
public class InitDatabase {
	@Bean
	CommandLineRunner init(MongoOperations operations) {
		return args -> {
			operations.dropCollection(Good.class);

			operations.insert(new Good("Good001","goodcode1", "goodname1", 100D));
			operations.insert(new Good("Good002","goodcode2", "goodname2", 200D));
			
			operations.findAll(Good.class).forEach(
					good -> {
						System.out.println(good.getId()
					);}
			);
		};
	}
}