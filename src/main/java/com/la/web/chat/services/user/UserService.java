package com.la.web.chat.services.user;

import java.util.Optional;

import com.la.web.chat.model.User;

public interface UserService {

	Optional<User> findByEmail(String email);

	Optional<User> findByUsername(String username);

}
