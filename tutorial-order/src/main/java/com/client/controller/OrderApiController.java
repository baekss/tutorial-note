package com.client.controller;

import com.client.dto.OrderDto;
import com.client.service.OrderService;
import com.client.vo.RequestOrder;
import com.client.vo.ResponseOrder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/order-service/api") // "/order-service" 는 gateway server 에서 Path=/order-service/** 설정에 의한 prefix
public class OrderApiController {

    private final Environment environment;
	private final OrderService orderService;
	private final ModelMapper mapper;

	@PostMapping("/users/{userId}/orders")
	public ResponseEntity<ResponseOrder> save(@PathVariable("userId") String userId, @RequestBody RequestOrder requestOrder) {
		OrderDto orderDto = mapper.map(requestOrder, OrderDto.class);
		orderDto.setUserId(userId);
		return ResponseEntity.status(HttpStatus.CREATED).body(orderService.createOrder(orderDto));
	}

	@GetMapping("/users/{userId}/orders")
	public ResponseEntity<List<ResponseOrder>> findOrderByUserId(@PathVariable("userId") String userId) {
		return ResponseEntity.ok(orderService.findOrdersByUserId(userId));
	}

	@GetMapping("/orders/{orderId}")
	public ResponseEntity<ResponseOrder> findOrderByOrderId(@PathVariable("orderId") String orderId) {
		return ResponseEntity.ok(orderService.findOrderByOrderId(orderId));
	}

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
