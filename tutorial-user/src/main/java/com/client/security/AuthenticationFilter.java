package com.client.security;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import com.client.vo.RequestLogin;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
		if (!MediaType.APPLICATION_JSON_VALUE.equals(request.getContentType())) {
			throw new IllegalArgumentException();
		}
		try {
			RequestLogin creds = Jackson2ObjectMapperBuilder.json()
				.build().readValue(request.getInputStream(), RequestLogin.class);
			return getAuthenticationManager()
				.authenticate(new UsernamePasswordAuthenticationToken(creds.getEmail(), creds.getPassword(), null));
		} catch (Exception e) {
			throw new RuntimeException();
		}
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult)
		throws IOException, ServletException {
		super.successfulAuthentication(request, response, chain, authResult);
	}
}
