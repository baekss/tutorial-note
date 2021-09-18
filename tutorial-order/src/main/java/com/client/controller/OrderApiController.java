package com.client.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order-service/api") // "/order-service" 는 gateway server 에서 Path=/order-service/** 설정에 의한 prefix
public class OrderApiController {

    @RequestMapping("/orders")
    public String findAll() {
        return "All_Orders";
    }
}
