package com.camposoft.security;

import java.io.IOException;
import java.security.Key;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.module.SimpleModule;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;

@Service
public class JWTAuthorizationFilter extends OncePerRequestFilter {

	private final String HEADER = "Authorization";
	private final static String PREFIX = "Bearer ";
	private static final Key SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS512);

	public static String getJWTToken(String username) throws JsonProcessingException {
		List<GrantedAuthority> grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList("ADMIN");
		Claims claims = Jwts.claims();
		claims.put("authorities", new ObjectMapper().writeValueAsString(grantedAuthorities));
		String token = Jwts.builder().setClaims(claims).setSubject(username).signWith(SECRET_KEY)
				.setExpiration(new Date(System.currentTimeMillis() + 7200000L)).compact();

		return PREFIX + token;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {
		try {
			if (checkJWTToken(request, response)) {
				Claims claims = validateToken(request);
				if (null != claims && null != claims.get("authorities")) {
					setUpSpringAuthentication(claims);
				} else {
					SecurityContextHolder.clearContext();
				}
			} else {
				SecurityContextHolder.clearContext();
			}
			chain.doFilter(request, response);
		} catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException e) {
			response.setStatus(HttpServletResponse.SC_FORBIDDEN);
			((HttpServletResponse) response).sendError(HttpServletResponse.SC_FORBIDDEN, e.getMessage());
			return;
		}
	}

	private Claims validateToken(HttpServletRequest request) {
		String jwt = request.getHeader("Authorization");
		try {
			return Jwts.parserBuilder().setSigningKey(SECRET_KEY).build().parseClaimsJws(jwt.replace(PREFIX, ""))
					.getBody();
		} catch (JwtException e) {
			logger.info(" <<-- validateToken -->> Error al Obtener token, el error es: " + e.getMessage());
			return null;
		}
	}

	@JsonDeserialize(using = SimpleGrantedAuthorityDeserializer.class)
	private void setUpSpringAuthentication(Claims claims) throws JsonParseException, JsonMappingException, IOException {

		ObjectMapper objectMapper = new ObjectMapper();

		objectMapper.registerModule(new SimpleModule().addDeserializer(SimpleGrantedAuthority.class,
				new SimpleGrantedAuthorityDeserializer()));
		String username = claims.getSubject();
		Object roles = claims.get("authorities");
		System.out.println(roles);
		Collection<? extends GrantedAuthority> authorities = Arrays
				.asList(objectMapper.readValue(roles.toString().getBytes(), SimpleGrantedAuthority[].class));

		UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(username, null, authorities);
		SecurityContextHolder.getContext().setAuthentication(auth);

	}

	private boolean checkJWTToken(HttpServletRequest request, HttpServletResponse res) {
		String authenticationHeader = request.getHeader(HEADER);
		if (authenticationHeader == null || !authenticationHeader.startsWith(PREFIX))
			return false;
		return true;
	}
}