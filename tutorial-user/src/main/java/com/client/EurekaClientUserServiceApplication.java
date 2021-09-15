package com.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class EurekaClientUserServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaClientUserServiceApplication.class, args);
    }
}
