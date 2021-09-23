package com.client.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/user-service/api")
public class UserApiController {

    private final Environment environment;

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
    public String check(HttpServletRequest request) {
        log.info("This is a message from User Service. port {}", request.getServerPort());
        return String.format("This is a message from User Service. port %s", environment.getProperty("local.server.port"));
    }
}
