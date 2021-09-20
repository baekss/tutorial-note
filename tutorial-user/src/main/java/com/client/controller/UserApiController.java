package com.client.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/user-service/api")
public class UserApiController {

    @GetMapping("/users")
    public String findAll() {
        return "All_Users";
    }

    @GetMapping("/message")
    public String message(@RequestHeader("user-request") String header) {
        log.info("header-value : {}", header);
        return header;
    }

    @GetMapping("/check")
    public String check() {
        log.info("This is a message from User Service");
        return "This is a message from User Service";
    }
}
