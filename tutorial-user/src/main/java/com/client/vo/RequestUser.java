package com.client.vo;

import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class RequestUser {
	@NotNull(message = "Email cannot be null")
	@Size(min = 2, message = "Email cannot be less than two characters")
	@Email
	private String email;

	@NotNull(message = "Name cannot be null")
	@Size(min = 2, message = "Name cannot be less than two characters")
	private String name;

	@NotNull(message = "Password cannot be null")
	@Size(min = 2, message = "Password must be equal or greater than 8 characters")
	private String password;
}
