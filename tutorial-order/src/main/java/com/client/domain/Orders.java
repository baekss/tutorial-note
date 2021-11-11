package com.client.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "orders")
public class Orders {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, length = 120)
	private String productId;

	@Column(nullable = false)
	private Integer quantity;

	@Column(nullable = false)
	private Integer unitPrice;

	@Column(nullable = false)
	private Integer totalPrice;

	@Column(nullable = false)
	private String userId;

	@Column(nullable = false, unique = true)
	private String orderId;

	@Column(nullable = false, insertable = false, updatable = false)
	@ColumnDefault(value = "CURRENT_TIMESTAMP")
	private LocalDate createAt;
}
