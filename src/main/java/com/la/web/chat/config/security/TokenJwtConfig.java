package com.la.web.chat.config.security;

import java.security.Key;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;


public class TokenJwtConfig {
	public static final Key SECRET = Keys.secretKeyFor(SignatureAlgorithm.HS256);
	public static final String PREFIX_TOKEN = "Bearer ";
	public static final String SECRET_KEY = "Authorization";
}

