package com.client.service;

import com.client.dto.OrderDto;
import com.client.vo.ResponseOrder;
import java.util.List;

public interface OrderService {
	ResponseOrder createOrder(OrderDto orderDto);
	ResponseOrder findOrderByOrderId(String orderId);
	List<ResponseOrder> findOrdersByUserId(String userId);
}
