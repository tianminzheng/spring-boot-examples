package com.springboot.testing;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.springboot.testing.model.User;
import com.springboot.testing.repository.UserRepository;

@ExtendWith(SpringExtension.class)
@DataMongoTest(excludeAutoConfiguration = EmbeddedMongoAutoConfiguration.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class LiveUserRepositoryTest {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	MongoOperations operations;

	@BeforeAll
	public void setUp() {
		operations.dropCollection(User.class);
		User user = new User("001", "tianyalan", 38, new Date(), "China");

		operations.insert(user);
	}

	@Test
	public void testFindUserById() throws Exception {
		String userId = "001";

		User user = userRepository.findUserById("001");
		assertThat(user).isNotNull();
		assertThat(user.getId()).isEqualTo(userId);
	}
}
