package com.client.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/order-service/api") // "/order-service" 는 gateway server 에서 Path=/order-service/** 설정에 의한 prefix
public class OrderApiController {

    @RequestMapping("/orders")
    public String findAll() {
        return "All_Orders";
    }

    @RequestMapping("/message")
    public String message(@RequestHeader("order-request") String header) {
        log.info("header-value : {}", header);
        return header;
    }
}
