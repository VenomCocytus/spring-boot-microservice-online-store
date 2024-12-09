package com.sehkmet.client.inventory.client;

import com.sehkmet.client.inventory.dto.VerifyStockRequest;
import com.sehkmet.core.common.GenericResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(value = "inventoryClient", url = "${inventory.url}")
public interface InventoryClient {

    @PostMapping("/api/inventory")
    ResponseEntity<GenericResponse<?>> isInStock(VerifyStockRequest verifyStockRequest);
}