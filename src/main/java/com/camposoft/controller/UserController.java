package com.camposoft.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.camposoft.security.JWTAuthorizationFilter;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/user")
public class UserController {

	@PostMapping("/login")
	public void login(@RequestParam("user") String username, @RequestParam("password") String pwd,
			HttpServletResponse response) throws IOException {

		String token = JWTAuthorizationFilter.getJWTToken(username);
		Map<String, Object> body = new HashMap<String, Object>();
		body.put("user", username);
		body.put("token", token);
		body.put("msg", "Loguin exitoso...!");

		response.getWriter().write(new ObjectMapper().writeValueAsString(body));
		response.setStatus(200);
		response.setContentType("application/json");

	}

}
