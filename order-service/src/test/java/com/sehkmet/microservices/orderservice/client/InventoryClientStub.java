package com.sehkmet.microservices.orderservice.client;

public class InventoryClientStub {

    public void stubInventoryCall(String skuCode, Integer quantity){
        stubFor(get(url))
    }
}
