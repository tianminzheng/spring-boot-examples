package com.springboot.testing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.springboot.testing.controller.UserController;
import com.springboot.testing.model.User;
import com.springboot.testing.service.UserService;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(UserController.class)
public class UserControllerTestsWithMockMvc {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private UserService userService;

	@Test
	public void testGetUserById() throws Exception {
		String userId = "001";

		User user = new User(userId, "tianyalan", 38, new Date(), "China");
		given(this.userService.findUserById(userId)).willReturn(user);

		this.mvc.perform(get("/users/" + userId).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}
}