package com.la.web.chat.config.security;

import java.util.ArrayList;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.la.web.chat.model.User;
import com.la.web.chat.repositories.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	private final UserRepository userRepository; // Tu repo de Mongo

	public CustomUserDetailsService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userRepository.findByEmail(email)
				.orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + email));

		// Convertir tu User a UserDetails de Spring Security
		return org.springframework.security.core.userdetails.User.builder().username(user.getUsername())
				.password(user.getPassword()) // debe estar encriptada
				.authorities(new ArrayList<>()) // si no usas roles, déjalo vacío o pon roles si tienes
				.build();
	}
}