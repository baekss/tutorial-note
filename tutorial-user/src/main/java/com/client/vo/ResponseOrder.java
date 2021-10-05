package com.client.vo;

import lombok.Data;
import java.time.LocalDate;

@Data
public class ResponseOrder {
	private String productId;
	private Integer quantity;
	private Integer unitPrice;
	private Integer totalPrice;
	private LocalDate createAt;

	private String orderId;
}
