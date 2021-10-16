package com.client.vo;

import lombok.Data;
import java.time.LocalDate;

@Data
public class ResponseCatalog {
	private String productId;
	private String productName;
	private Integer unitPrice;
	private Integer stock;
	private LocalDate createAt;
}
