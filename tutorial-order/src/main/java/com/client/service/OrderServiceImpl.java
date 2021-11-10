package com.client.service;

import static java.util.stream.Collectors.toList;

import com.client.domain.OrderRepository;
import com.client.domain.Orders;
import com.client.dto.OrderDto;
import com.client.vo.ResponseOrder;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

	private final OrderRepository orderRepository;
	private final ModelMapper mapper;

	@Override
	public ResponseOrder createOrder(OrderDto orderDto) {
		orderDto.setOrderId(UUID.randomUUID().toString());
		orderDto.setTotalPrice(orderDto.getQuantity() * orderDto.getUnitPrice());

		Orders order = mapper.map(orderDto, Orders.class);

		return mapper.map(orderRepository.save(order), ResponseOrder.class);
	}

	@Override
	public ResponseOrder findOrderByOrderId(String orderId) {
		return mapper.map(orderRepository.findByOrderId(orderId), ResponseOrder.class);
	}

	@Override
	public List<ResponseOrder> findOrderByUserId(String userId) {
		return orderRepository.findByUserId(userId).stream()
			.map(this::getResponseOrder)
			.collect(toList());
	}

	private ResponseOrder getResponseOrder(Orders order) {
		return mapper.map(order, ResponseOrder.class);
	}
}
