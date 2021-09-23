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
@RequestMapping("/order-service/api") // "/order-service" 는 gateway server 에서 Path=/order-service/** 설정에 의한 prefix
public class OrderApiController {

    private final Environment environment;

    @GetMapping("/orders")
    public String findAll() {
        return "All_Orders";
    }

    @GetMapping("/message")
    public String message(@RequestHeader("order-request") String header) {
        log.info("header-value : {}", header);
        return header;
    }

    @GetMapping("/check")
    public String check(HttpServletRequest request) {
        log.info("This is a message from Order Service. port {}", request.getServerPort());
        return String.format("This is a message from Order Service. port %s", environment.getProperty("local.server.port"));
    }
}
