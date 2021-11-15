package com.client.service;

import com.client.dto.UserDto;
import com.client.vo.ResponseUser;
import org.springframework.security.core.userdetails.UserDetailsService;
import java.util.List;

public interface UserService extends UserDetailsService {
	ResponseUser createUser(UserDto userDto);
	List<ResponseUser> findAll();
	ResponseUser findByUserId(String userId);
}
