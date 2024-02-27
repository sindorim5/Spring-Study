package com.sindorim.redisdemo.adapter.in;

import java.util.HashMap;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sindorim.redisdemo.application.service.UserService;
import com.sindorim.redisdemo.domain.User;

@RestController
@RequestMapping("/api/users")
public class UserController {
	private final UserService userService;
	
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@PostMapping("/{key}")
	public User saveUser(
			@PathVariable String key,
			@RequestBody User user
		) {
		return userService.saveUser(key, user);
	}
	
	@GetMapping("/{key}")
	public User getUser(@PathVariable String Key) {
		return userService.getUser(Key);
	}
}
