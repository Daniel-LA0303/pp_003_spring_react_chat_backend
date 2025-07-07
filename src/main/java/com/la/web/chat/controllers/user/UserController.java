package com.la.web.chat.controllers.user;

import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.la.web.chat.config.security.JwtUtil;
import com.la.web.chat.config.security.TokenJwtConfig;
import com.la.web.chat.model.User;
import com.la.web.chat.services.user.UserService;
import com.la.web.chat.utils.dtos.auth.LoginUserDTO;
import com.la.web.chat.utils.dtos.auth.UserDTO;

@RestController
@RequestMapping("/users")
@CrossOrigin("http://localhost:5173")
public class UserController {

	private final UserService userService;
	private final JwtUtil jwtUtil;

	public UserController(UserService userService, JwtUtil jwtUtil) {
		this.userService = userService;
		this.jwtUtil = jwtUtil;
	}

	// controller que se encarga de validar el JWT
	@GetMapping("/me")
	public ResponseEntity<?> getCurrentUser(@RequestHeader(TokenJwtConfig.SECRET_KEY) String authHeader) {
		try {
			// Quitar "Bearer " del header para obtener solo el token
			String token = authHeader.replace(TokenJwtConfig.PREFIX_TOKEN, "");
			String email = jwtUtil.extractUsername(token); // el email está como subject
			User user = userService.findByEmail(email).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
			return ResponseEntity.ok(user);
		} catch (Exception e) {
			return buildErrorResponse("Token inválido o expirado", HttpStatus.UNAUTHORIZED);
		}
	}

	@PostMapping("/login")
	public ResponseEntity<?> loginUser(@RequestBody LoginUserDTO loginUserDTO) {
		try {
			User authenticatedUser = userService.loginUser(loginUserDTO);
			String token = jwtUtil.generateToken(authenticatedUser.getEmail());

			return ResponseEntity.ok().header(TokenJwtConfig.SECRET_KEY, TokenJwtConfig.PREFIX_TOKEN + token)
					.body(Map.of("token", token, "username", authenticatedUser.getUsername()));
		} catch (RuntimeException e) {
			return buildErrorResponse(e.getMessage(), HttpStatus.UNAUTHORIZED);
		}
	}

	@PostMapping("/register")
	public ResponseEntity<?> registerUser(@RequestBody UserDTO userDTO) {
		try {
			User createdUser = userService.createUser(userDTO);
			String token = jwtUtil.generateToken(createdUser.getEmail());

			return ResponseEntity.status(HttpStatus.CREATED)
					.header(TokenJwtConfig.SECRET_KEY, TokenJwtConfig.PREFIX_TOKEN + token)
					.body(Map.of("user", createdUser, "token", token));
		} catch (RuntimeException e) {
			return buildErrorResponse(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	private ResponseEntity<Map<String, Object>> buildErrorResponse(String message, HttpStatus status) {
		return ResponseEntity.status(status).body(Map.of("error", message, "timestamp", LocalDateTime.now()));
	}
}