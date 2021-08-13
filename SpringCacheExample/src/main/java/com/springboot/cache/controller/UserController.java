package com.springboot.cache.controller;

import com.springboot.cache.entity.User;
import com.springboot.cache.service.UserService;
import com.springboot.cache.utils.ResultModel;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/user")
public class UserController {
	@Autowired
	protected UserService userService;

	@GetMapping(value = "/list")
	public ResultModel getList(@RequestParam int pageSize, @RequestParam int pageNumber) {
		if (pageSize > 0) {
			Page<User> page = userService.findAll(pageSize, pageNumber);
			return ResultModel.ok(page);

		} else {
			List<User> entities = userService.findAll();
			return ResultModel.ok(entities);
		}
	}

	@PostMapping(value = "/")
	public ResultModel add(@RequestBody User user) {

		userService.save(user);
		return ResultModel.ok();
	}

	@GetMapping(value = "/{id}")
	public ResultModel findOne(@PathVariable Long id) {

		return ResultModel.ok(userService.findOne(id));
	}

	@PostMapping(value = "/{id}")
	public ResultModel update(@RequestBody User user, @PathVariable Long id) {
		Optional<User> optional = userService.findOne(id);
		if (optional.isPresent()) {
			User old = optional.get();
			user.setId(id);
			user.setValid(old.isValid());
			userService.save(user);
		}
		return ResultModel.ok();
	}

	@DeleteMapping(value = "/{id}")
	public ResultModel delete(@PathVariable Long id) {
		userService.delete(id);
		return ResultModel.ok();
	}
}
