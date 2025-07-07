package com.la.web.chat.config.security;

import java.util.Date;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;

@Component
public class JwtUtil {
	private static final long EXPIRATION_TIME = 1000L * 60 * 60 * 24 * 7; // 7 días

	public String extractUsername(String token) {
		return Jwts.parserBuilder().setSigningKey(TokenJwtConfig.SECRET).build().parseClaimsJws(token).getBody()
				.getSubject();
	}

	public String generateToken(String username) {
		return Jwts.builder().setSubject(username).setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME)).signWith(TokenJwtConfig.SECRET)
				.compact();
	}
}
