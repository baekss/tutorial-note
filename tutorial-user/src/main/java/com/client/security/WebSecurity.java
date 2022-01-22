package com.client.security;

import com.client.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurity extends WebSecurityConfigurerAdapter {

	private final UserService userService;
	private final BCryptPasswordEncoder passwordEncoder;
	private final Environment env;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.authorizeRequests().antMatchers("/**")
			.hasIpAddress("127.0.0.1")
			.and().addFilter(getAuthenticationFilter());
		http.headers().frameOptions().disable();
	}

	private AuthenticationFilter getAuthenticationFilter() throws Exception {
		return new AuthenticationFilter(authenticationManager(), env); // 인증 처리 필터
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// UserDetailsService#loadUserByUsername 구현하고 있는 Component와 암호 비교시 사용할 Encoder
		auth.userDetailsService(userService).passwordEncoder(passwordEncoder);
	}
}
