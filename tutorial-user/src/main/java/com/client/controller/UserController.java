package com.client.controller;

import com.client.vo.Greeting;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user-service")
public class UserController {

	private final Environment env;
	private final Greeting greeting;

	@GetMapping("/health_check")
	public String status() {
		return String.format("It's Working on port %s", env.getProperty("local.server.port"));
	}

	@GetMapping("/welcome")
	public String welcome() {
		// return env.getProperty("greeting.message");
		return greeting.getMessage();
	}

}
