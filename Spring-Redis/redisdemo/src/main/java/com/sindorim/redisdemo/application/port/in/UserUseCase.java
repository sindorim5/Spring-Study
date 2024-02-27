package com.sindorim.redisdemo.application.port.in;

import com.sindorim.redisdemo.domain.User;

public interface UserUseCase {
	User saveUser(String key, User user);
	User getUser(String key);
}
