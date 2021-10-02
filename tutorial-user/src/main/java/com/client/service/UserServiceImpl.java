package com.client.service;

import static java.util.stream.Collectors.toList;

import com.client.dto.UserDto;
import com.client.repository.User;
import com.client.repository.UserRepositorty;
import com.client.vo.ResponseUser;
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
	public ResponseUser createUser(UserDto userDto) {
		userDto.setUserId(UUID.randomUUID().toString());

		ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		User user = mapper.map(userDto, User.class);
		user.setEncryptedPwd("");

		return mapper.map(userRepositorty.save(user), ResponseUser.class);
	}

	@Override
	public List<ResponseUser> findAll() {
		ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		return userRepositorty.findAll()
			.stream()
			.map(user -> mapper.map(user, ResponseUser.class))
			.collect(toList());
	}
}
