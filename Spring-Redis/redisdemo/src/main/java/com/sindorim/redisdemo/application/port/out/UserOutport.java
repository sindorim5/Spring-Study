package com.sindorim.redisdemo.application.port.out;

import java.util.HashMap;

import com.sindorim.redisdemo.domain.User;

public interface UserOutport {
	User saveUser(String key, User user);
	User getUser(String key);
}
