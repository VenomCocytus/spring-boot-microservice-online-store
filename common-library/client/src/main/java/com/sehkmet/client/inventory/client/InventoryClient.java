package com.sehkmet.client.inventory.client;

import com.sehkmet.client.inventory.dto.VerifyStockRequest;
import com.sehkmet.core.common.GenericResponse;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.PostExchange;

import static com.sehkmet.utils.utils.Utils.translate;

public interface InventoryClient {
    Logger LOGGER = LoggerFactory.getLogger(InventoryClient.class);

    @PostExchange("/api/inventory")
    @CircuitBreaker(name = "inventory", fallbackMethod = "fallbackMethod")
    @Retry(name = "inventoryRetry")
    ResponseEntity<GenericResponse<?>> isInStock(@RequestBody VerifyStockRequest verifyStockRequest);

    default ResponseEntity<GenericResponse<?>> fallbackMethod(VerifyStockRequest verifyStockRequest, Throwable throwable) {
        LOGGER.info("Cannot get inventory for skuCode {}, failure reason: {}", verifyStockRequest.skuCode(), throwable.getMessage());
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(GenericResponse.error(
                        translate("exception.product-not-in-stock", verifyStockRequest.skuCode())));
    }
}