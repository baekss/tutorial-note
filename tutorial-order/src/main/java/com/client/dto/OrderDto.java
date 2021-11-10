package com.client.dto;

import lombok.Data;

@Data
public class OrderDto {
	private String productId;
	private Integer quantity;
	private Integer unitPrice;
	private Integer totalPrice;
	private String userId;
	private String orderId;
}
