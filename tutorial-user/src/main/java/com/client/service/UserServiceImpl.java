package com.client.service;

import static java.util.stream.Collectors.toList;

import com.client.dto.UserDto;
import com.client.domain.User;
import com.client.domain.UserRepository;
import com.client.vo.ResponseUser;
import java.util.List;
import java.util.UUID;
import javax.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;
	private final BCryptPasswordEncoder passwordEncoder;

	@Override
	public ResponseUser createUser(UserDto userDto) {
		userDto.setUserId(UUID.randomUUID().toString());

		ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		User user = mapper.map(userDto, User.class);
		user.setEncryptedPwd(passwordEncoder.encode(userDto.getPassword()));

		return mapper.map(userRepository.save(user), ResponseUser.class);
	}

	@Override
	public List<ResponseUser> findAll() {
		ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		return userRepository.findAll()
			.stream()
			.map(user -> getResponseUser(mapper, user))
			.collect(toList());
	}

	@Override
	public ResponseUser findByUserId(String userId) {
		ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		return userRepository.findByUserId(userId)
			.map(user -> getResponseUser(mapper, user))
			.orElseThrow(EntityNotFoundException::new);
	}

	private ResponseUser getResponseUser(ModelMapper mapper, User user) {
		ResponseUser res = mapper.map(user, ResponseUser.class);
		res.setOrders(List.of());
		return res;
	}
}
