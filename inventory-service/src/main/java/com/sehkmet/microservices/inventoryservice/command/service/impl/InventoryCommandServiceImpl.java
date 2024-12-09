package com.sehkmet.microservices.inventoryservice.command.service.impl;

import com.sehkmet.microservices.inventoryservice.command.dto.VerifyStockRequest;
import com.sehkmet.microservices.inventoryservice.command.service.InventoryCommandService;
import com.sehkmet.microservices.inventoryservice.exception.runtime.ProductNotInStockException;
import com.sehkmet.microservices.inventoryservice.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.sehkmet.utils.utils.Utils.translate;

@Service
@Slf4j
@RequiredArgsConstructor
public class InventoryCommandServiceImpl implements InventoryCommandService {

    private final InventoryRepository inventoryRepository;

    @Transactional(readOnly = true)
    public boolean isInStock(VerifyStockRequest verifyStockRequest) {

        String skuCode = verifyStockRequest.skuCode();
        int quantity = Integer.parseInt(verifyStockRequest.quantity());

        // Find an inventory for a given skuCode where quantity is inferior or equal to the max available quantity
        log.info(" Start -- Received request to check stock for skuCode {}, with quantity {}", skuCode, quantity);
        boolean isInStock = inventoryRepository.existsBySkuCodeAndQuantityIsGreaterThanEqual(skuCode, quantity);
        log.info(" End -- Product with skuCode {}, and quantity {}, is in stock - {}", skuCode, quantity, isInStock);

        if(!isInStock) throw new ProductNotInStockException(translate("exception.inventory-not-in-stock", skuCode));

        return true;
    }
}
