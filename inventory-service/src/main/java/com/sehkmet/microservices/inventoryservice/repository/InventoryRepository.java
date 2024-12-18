package com.sehkmet.microservices.inventoryservice.repository;

import com.sehkmet.microservices.inventoryservice.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    boolean existsBySkuCodeAndQuantityIsGreaterThanEqual(String skuCode, Integer quantity);
    boolean existsBySkuCode(String skuCode);
}
