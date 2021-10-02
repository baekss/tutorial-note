package com.client.service;

import com.client.dto.UserDto;
import com.client.vo.ResponseUser;
import java.util.List;

public interface UserService {
	ResponseUser createUser(UserDto userDto);
	List<ResponseUser> findAll();
}
