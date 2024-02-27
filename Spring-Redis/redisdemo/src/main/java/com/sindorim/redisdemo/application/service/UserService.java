package com.sindorim.redisdemo.application.service;

import java.util.HashMap;

import org.springframework.stereotype.Service;

import com.sindorim.redisdemo.application.port.in.UserUseCase;
import com.sindorim.redisdemo.application.port.out.UserOutport;
import com.sindorim.redisdemo.domain.User;

@Service
public class UserService implements UserUseCase {
	
	private final UserOutport userRepo;
	
	public UserService(UserOutport userRepo) {
		this.userRepo = userRepo;
	}

	@Override
	public User saveUser(String key, User user) {
		return userRepo.saveUser(key, user);
	}

	@Override
	public User getUser(String key) {
		return userRepo.getUser(key);
	}

	
}
