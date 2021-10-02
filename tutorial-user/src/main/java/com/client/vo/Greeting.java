package com.client.vo;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class Greeting {

	@Value("${greeting.message}")
	private String message;

}
