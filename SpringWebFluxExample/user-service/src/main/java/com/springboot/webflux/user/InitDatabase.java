
package com.springboot.webflux.user;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Component;

import com.springboot.webflux.user.domain.User;

@Component
public class InitDatabase {
	@Bean
	CommandLineRunner init(MongoOperations operations) {
		return args -> {
			operations.dropCollection(User.class);

			operations.insert(new User("User001","usercode1", "username1"));
			operations.insert(new User("User002","usercode2", "username2"));
			
			operations.findAll(User.class).forEach(
					user -> {
						System.out.println(user.getId()
					);}
			);
		};
	}
}