package com.sindorim.redisdemo.adapter.out;

import java.util.HashMap;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.sindorim.redisdemo.application.port.out.UserOutport;
import com.sindorim.redisdemo.domain.User;

@Repository
public class RedisRepository implements UserOutport {
	private final RedisTemplate<String, User> redisTemplate;

	public RedisRepository(RedisTemplate<String, User> redisTemplate) {
		this.redisTemplate = redisTemplate;
	}

	@Override
	public User saveUser(String key, User user) {
		redisTemplate.opsForValue().set(key, user);

		return user;
	}

	@Override
	public User getUser(String key) {
		return redisTemplate.opsForValue().get(key);
	}

}
