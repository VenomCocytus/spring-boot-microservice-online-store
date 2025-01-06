package com.sehkmet.microservices.orderservice.repository;

import com.sehkmet.microservices.orderservice.model.Order;
import io.micrometer.observation.annotation.Observed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Observed
@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
