package com.springboot.testing;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.springboot.testing.model.User;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;

@ExtendWith(SpringExtension.class)
public class UserTests {

	private static final String USER_NAME = "tianyalan";

	@Test
	public void testUsernameIsMoreThan5Chars() throws Exception {

		User user = new User("001", USER_NAME, 38, new Date(), "China");

		assertThat(user.getName()).isEqualTo(USER_NAME);
	}
}
