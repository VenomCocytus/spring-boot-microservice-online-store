package com.sehkmet.client.inventory.client;

import com.sehkmet.client.inventory.dto.VerifyStockRequest;
import com.sehkmet.core.common.GenericResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.service.annotation.GetExchange;

import static com.sehkmet.utils.utils.Utils.translate;
@FeignClient(value = "inventoryClient", url = "${inventory.url}")
public interface InventoryClient {
    Logger log = LoggerFactory.getLogger(InventoryClient.class);

    @GetExchange("/api/inventory")
    @CircuitBreaker(name = "inventory", fallbackMethod = "fallbackMethod")
    @Retry(name = "inventoryRetry")
    ResponseEntity<GenericResponse<?>> isInStock(VerifyStockRequest verifyStockRequest);

    default ResponseEntity<GenericResponse<?>> fallbackMethod(VerifyStockRequest verifyStockRequest, Throwable throwable) {
        log.info("Cannot get inventory for skuCode {}, failure reason: {}", verifyStockRequest.skuCode(), throwable.getMessage());
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(GenericResponse.error(
                        translate("exception.product-not-in-stock", verifyStockRequest.skuCode())));
    }
}