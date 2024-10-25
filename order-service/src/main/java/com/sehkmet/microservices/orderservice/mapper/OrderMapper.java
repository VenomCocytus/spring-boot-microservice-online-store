package com.sehkmet.microservices.orderservice.mapper;

import com.sehkmet.microservices.orderservice.command.dto.PlaceOrderRequest;
import com.sehkmet.microservices.orderservice.model.Order;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    Order mapToOrder(PlaceOrderRequest placeOrderRequest);
}
