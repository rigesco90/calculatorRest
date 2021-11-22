package com.camposoft.controller;

import java.security.Key;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.camposoft.dto.UserDTO;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@RestController
@RequestMapping("/user")
public class UserController {

	private static final Key SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS512);
	private static final String BEARER_PREFIX = "Bearer ";

	@PostMapping("/login")
	public UserDTO login(@RequestParam("user") String username, @RequestParam("password") String pwd) {

		String token = getJWTToken(username);
		UserDTO user = new UserDTO();
		user.setUser(username);
		user.setToken(token);
		return user;

	}

	private String getJWTToken(String username) {
		List<GrantedAuthority> grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList("ADMIN");

		String token = Jwts.builder().setId("C4lcul4t0r").setSubject(username)
				.claim("authorities",
						grantedAuthorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 600000)).signWith(SECRET_KEY).compact();

		return BEARER_PREFIX + token;
	}

}
