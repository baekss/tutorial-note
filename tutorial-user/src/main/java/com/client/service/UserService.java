package com.client.service;

import com.client.dto.UserDto;
import java.util.List;

public interface UserService {
	UserDto createUser(UserDto userDto);
	List<UserDto> findAll();
}
