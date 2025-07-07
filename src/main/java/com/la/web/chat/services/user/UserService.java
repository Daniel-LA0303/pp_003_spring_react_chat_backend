package com.la.web.chat.services.user;

import java.util.Optional;

import com.la.web.chat.model.User;
import com.la.web.chat.utils.dtos.auth.LoginUserDTO;
import com.la.web.chat.utils.dtos.auth.UserDTO;

public interface UserService {

	public User createUser(UserDTO userDTO);

	public User loginUser(LoginUserDTO loginUserDTO);

	Optional<User> findByEmail(String email);

	Optional<User> findByUsername(String username);

}
