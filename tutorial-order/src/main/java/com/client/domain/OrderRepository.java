package com.client.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface OrderRepository extends JpaRepository<Orders, Long> {
	Orders findByOrderId(String orderId);
	List<Orders> findByUserId(String userId);
}
