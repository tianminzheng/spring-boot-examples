package com.springboot.testing;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.springboot.testing.model.User;
import com.springboot.testing.repository.UserRepository;
import com.springboot.testing.service.UserService;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class UserServiceTests {

	@MockBean
	private UserRepository userRepository;

	@Autowired
	private UserService userService;
	
	@Test
	public void testFindUserById() throws Exception {

		String userId = "001";

		User user = new User(userId, "tianyalan", 38, new Date(), "China");
		
		Mockito.when(userRepository.findUserById(userId)).thenReturn(user);
        		
		User actual = userService.findUserById(userId);

		assertThat(actual).isNotNull();
		assertThat(actual.getId()).isEqualTo(userId);
	}
}