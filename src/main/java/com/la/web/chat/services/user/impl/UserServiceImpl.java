package com.la.web.chat.services.user.impl;

import java.util.Optional;

import com.la.web.chat.model.User;
import com.la.web.chat.repositories.UserRepository;
import com.la.web.chat.services.user.UserService;

public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;

	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public Optional<User> findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public Optional<User> findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

}
