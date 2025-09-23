package com.la.web.chat.controllers.user;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.la.web.chat.config.security.JwtUtil;
import com.la.web.chat.config.security.TokenJwtConfig;
import com.la.web.chat.model.User;
import com.la.web.chat.services.user.UserService;
import com.la.web.chat.utils.constants.PathsConstants;
import com.la.web.chat.utils.dtos.auth.LoginUserDTO;
import com.la.web.chat.utils.dtos.auth.UserDTO;
import com.la.web.chat.utils.enums.MethodEnum;
import com.la.web.chat.utils.enums.ResponseStatus;
import com.la.web.chat.utils.exceptions.ServiceException;
import com.la.web.chat.utils.mappers.MessageFormatter;
import com.la.web.chat.utils.response.ApiResponse;

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
	public ResponseEntity<?> loginUser(@RequestBody LoginUserDTO loginUserDTO) throws ServiceException {
		// 1. Autenticar al usuario (puede lanzar ServiceException)
		User authenticatedUser = userService.loginUser(loginUserDTO);

		// 2. Generar token JWT
		String token = jwtUtil.generateToken(authenticatedUser.getEmail());

		// 3. Construir respuesta estructurada con ApiResponse
		ApiResponse<Map<String, String>> apiResponse = new ApiResponse<>(ResponseStatus.SUCCESS.getHttpStatusCode(), // Código
																														// 200
				PathsConstants.PATH_AUTH, // Ej: "/api/login"
				MethodEnum.POST, MessageFormatter.formatMessage(ResponseStatus.SUCCESS, "Login exitoso"),
				Map.of("token", token, "username", authenticatedUser.getUsername()), false);

		// 4. Devolver respuesta con header de autenticación
		return ResponseEntity.ok().header(TokenJwtConfig.SECRET_KEY, TokenJwtConfig.PREFIX_TOKEN + token)
				.body(apiResponse);
	}

	@PostMapping("/register")
	public ResponseEntity<?> registerUser(@RequestBody UserDTO userDTO) throws ServiceException {
		// 1. Crear usuario y generar token
		User createdUser = userService.createUser(userDTO); // Puede lanzar ServiceException
		String token = jwtUtil.generateToken(createdUser.getEmail());

		// 2. Construir respuesta exitosa
		ApiResponse<Map<String, Object>> apiResponse = new ApiResponse<>(ResponseStatus.CREATED.getHttpStatusCode(),
				PathsConstants.PATH_USER, MethodEnum.POST,
				MessageFormatter.formatMessage(ResponseStatus.CREATED, "User"),
				Map.of("user", createdUser, "token", token), false);

		// 3. Devolver respuesta con headers
		return ResponseEntity.status(HttpStatus.CREATED)
				.header(TokenJwtConfig.SECRET_KEY, TokenJwtConfig.PREFIX_TOKEN + token).body(apiResponse);
	}

	@GetMapping("/search")
	public ResponseEntity<?> searchUsers(@RequestParam("query") String query) {
		List<User> matchedUsers = userService.searchUsers(query);

		ApiResponse<List<User>> apiResponse = new ApiResponse<>(ResponseStatus.SUCCESS.getHttpStatusCode(),
				PathsConstants.PATH_USER, MethodEnum.GET,
				MessageFormatter.formatMessage(ResponseStatus.SUCCESS, "Found users"), matchedUsers, false);

		return ResponseEntity.ok(apiResponse);
	}

	private ResponseEntity<Map<String, Object>> buildErrorResponse(String message, HttpStatus status) {
		return ResponseEntity.status(status).body(Map.of("error", message, "timestamp", LocalDateTime.now()));
	}
}