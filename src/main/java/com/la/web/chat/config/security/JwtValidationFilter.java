package com.la.web.chat.config.security;

import java.util.List;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.IOException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtValidationFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
			throws ServletException, IOException, java.io.IOException {

		String header = req.getHeader(TokenJwtConfig.SECRET_KEY);

		if (header == null || !header.startsWith(TokenJwtConfig.PREFIX_TOKEN)) {
			chain.doFilter(req, res);
			return;
		}

		String token = header.replace(TokenJwtConfig.PREFIX_TOKEN, "");

		try {
			Claims claims = Jwts.parserBuilder().setSigningKey(TokenJwtConfig.SECRET).build().parseClaimsJws(token)
					.getBody();

			String username = claims.getSubject();

			if (username != null) {
				UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(username, null,
						List.of());
				SecurityContextHolder.getContext().setAuthentication(auth);
			}

			chain.doFilter(req, res);

		} catch (JwtException e) {
			res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			res.setContentType("application/json");
			res.getWriter().write("{\"error\":\"Token inválido o expirado\"}");
		}
	}
}