package com.client.service;

import static java.util.stream.Collectors.toList;

import com.client.domain.User;
import com.client.domain.UserRepository;
import com.client.dto.UserDto;
import com.client.security.SecurityUser;
import com.client.vo.ResponseUser;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import javax.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;
	private final BCryptPasswordEncoder passwordEncoder;
	private final ModelMapper mapper;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userRepository.findByEmail(email)
			.orElseThrow(EntityNotFoundException::new);
		return new SecurityUser(user.getUserId(), user.getEmail(), user.getEncryptedPwd(), true, true, true, true, Set.of());
	}

	@Override
	public ResponseUser createUser(UserDto userDto) {
		userDto.setUserId(UUID.randomUUID().toString());

		User user = mapper.map(userDto, User.class);
		user.setEncryptedPwd(passwordEncoder.encode(userDto.getPassword()));

		return mapper.map(userRepository.save(user), ResponseUser.class);
	}

	@Override
	public List<ResponseUser> findAll() {
		return userRepository.findAll()
			.stream()
			.map(this::getResponseUser)
			.collect(toList());
	}

	@Override
	public ResponseUser findByUserId(String userId) {
		return userRepository.findByUserId(userId)
			.map(this::getResponseUser)
			.orElseThrow(EntityNotFoundException::new);
	}

	private ResponseUser getResponseUser(User user) {
		ResponseUser res = mapper.map(user, ResponseUser.class);
		res.setOrders(List.of());
		return res;
	}
}
