package com.sehkmet.microservices.inventoryservice.repository;

import com.sehkmet.microservices.inventoryservice.model.Inventory;
import io.micrometer.observation.annotation.Observed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Observed
@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    boolean existsBySkuCodeAndQuantityIsGreaterThanEqual(String skuCode, Integer quantity);
    boolean existsBySkuCode(String skuCode);
}
