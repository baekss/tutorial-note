package com.client.controller;

import com.client.dto.UserDto;
import com.client.service.UserService;
import com.client.vo.RequestUser;
import com.client.vo.ResponseUser;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/user-service/api")
public class UserApiController {

    private final Environment environment;
	private final UserService userService;

	@PostMapping("/users")
	public ResponseEntity<ResponseUser> save(@RequestBody RequestUser requestUser) {
		ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(mapper.map(requestUser, UserDto.class)));
	}

	@GetMapping("/users")
	public ResponseEntity<List<ResponseUser>> findAll() {
		return ResponseEntity.ok(userService.findAll());
	}

    @GetMapping("/message")
    public String message(@RequestHeader("user-request") String header) {
        log.info("header-value : {}", header);
        return header;
    }

	@GetMapping("/check")
    public String check(HttpServletRequest request) {
        log.info("This is a message from User Service. port {}", request.getServerPort());
        return String.format("This is a message from User Service. port %s", environment.getProperty("local.server.port"));
    }
}
