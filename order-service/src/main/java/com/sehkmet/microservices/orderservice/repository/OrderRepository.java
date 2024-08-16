package com.sehkmet.microservices.orderservice.repository;

import com.sehkmet.microservices.orderservice.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
