package com.client.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user-service/api")
public class UserApiController {

    @RequestMapping("/users")
    public String findAll() {
        return "All_User";
    }
}
