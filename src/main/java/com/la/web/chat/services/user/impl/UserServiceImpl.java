package com.la.web.chat.services.user.impl;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.la.web.chat.model.User;
import com.la.web.chat.repositories.UserRepository;
import com.la.web.chat.services.user.UserService;
import com.la.web.chat.utils.constants.PathsConstants;
import com.la.web.chat.utils.dtos.auth.LoginUserDTO;
import com.la.web.chat.utils.dtos.auth.UserDTO;
import com.la.web.chat.utils.enums.MethodEnum;
import com.la.web.chat.utils.enums.ResponseStatus;
import com.la.web.chat.utils.exceptions.ServiceException;
import com.la.web.chat.utils.mappers.MessageFormatter;

@Service
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public User createUser(UserDTO userDTO) {
		if (userRepository.findByEmail(userDTO.getEmail()).isPresent()) {
			throw new RuntimeException("El email ya está registrado");
		}

		if (userRepository.findByUsername(userDTO.getUsername()).isPresent()) {
			throw new RuntimeException("El nombre de usuario ya existe");
		}

		User newUser = new User();
		newUser.setUsername(userDTO.getUsername());
		newUser.setEmail(userDTO.getEmail());
		newUser.setPassword(passwordEncoder.encode(userDTO.getPassword()));

		return userRepository.save(newUser);
	}

	@Override
	public Optional<User> findByEmail(String email) throws ServiceException {

		Optional<User> user = userRepository.findByEmail(email);

		if (user.isEmpty()) {
			throw new ServiceException(MessageFormatter.formatMessage(ResponseStatus.NOT_FOUND, "User"),
					ResponseStatus.NOT_FOUND.getHttpStatusCode(), PathsConstants.PATH_USER, MethodEnum.GET);
		}

		return user;
	}

	@Override
	public Optional<User> findByUsername(String username) throws ServiceException {

		Optional<User> user = userRepository.findByUsername(username);

		if (user.isEmpty()) {
			throw new ServiceException(MessageFormatter.formatMessage(ResponseStatus.NOT_FOUND, "User"),
					ResponseStatus.NOT_FOUND.getHttpStatusCode(), PathsConstants.PATH_USER, MethodEnum.GET);
		}

		return user;
	}

	@Override
	public User loginUser(LoginUserDTO loginUserDTO) {
		User user = userRepository.findByEmail(loginUserDTO.getEmail())
				.orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

		if (!passwordEncoder.matches(loginUserDTO.getPassword(), user.getPassword())) {
			throw new RuntimeException("Contraseña incorrecta");
		}

		return user;
	}
}
