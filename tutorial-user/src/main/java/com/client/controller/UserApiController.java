package com.client.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/user-service/api")
public class UserApiController {

    @RequestMapping("/users")
    public String findAll() {
        return "All_User";
    }

    @RequestMapping("/message")
    public String message(@RequestHeader("user-request") String header) {
        log.info("header-value : {}", header);
        return header;
    }
}
