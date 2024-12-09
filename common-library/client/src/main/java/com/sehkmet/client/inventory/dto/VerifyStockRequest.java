package com.sehkmet.client.inventory.dto;

public record VerifyStockRequest(
        String skuCode,
        String quantity
) {}
