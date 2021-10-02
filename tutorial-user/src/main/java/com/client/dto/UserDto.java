package com.client.dto;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
public class UserDto {
	private String email;
	private String name;
	private String pwd;
	private String userId;
	private LocalDate createdAt;

	private String encryptedPwd;
}
