package com.client.service;

import static java.util.stream.Collectors.toList;

import com.client.dto.UserDto;
import com.client.repository.User;
import com.client.repository.UserRepositorty;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	private final UserRepositorty userRepositorty;

	@Override
	public UserDto createUser(UserDto userDto) {
		userDto.setUserId(UUID.randomUUID().toString());

		ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		User user = mapper.map(userDto, User.class);
		user.changeEncryptedPwd(user.getEncryptedPwd());

		userRepositorty.save(user);
		return userDto;
	}

	@Override
	public List<UserDto> findAll() {
		ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		return userRepositorty.findAll()
			.stream()
			.map(user -> mapper.map(user, UserDto.class))
			.collect(toList());
	}
}
