package com.springboot.testing;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.springboot.testing.model.User;
import com.springboot.testing.service.UserService;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerTestsWithTestRestTemplate {

	@Autowired
	private TestRestTemplate testRestTemplate;

	@MockBean
	private UserService userService;

	@Test
	public void testGetUserById() throws Exception {
		String userId = "001";

		User user = new User(userId, "tianyalan", 38, new Date(), "China");
		given(this.userService.findUserById(userId)).willReturn(user);

		User actual = testRestTemplate.getForObject("/users/" + userId, User.class);
		assertThat(actual.getId()).isEqualTo(userId);
	}
}