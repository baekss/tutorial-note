package com.client.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/order-service/api") // "/order-service" 는 gateway server 에서 Path=/order-service/** 설정에 의한 prefix
public class OrderApiController {

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
    public String check() {
        log.info("This is a message from Order Service");
        return "This is a message from Order Service";
    }
}
