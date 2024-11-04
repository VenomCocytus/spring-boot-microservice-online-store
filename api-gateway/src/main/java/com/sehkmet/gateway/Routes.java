package com.sehkmet.gateway;

import org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions;
import org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RequestPredicates;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

@Configuration
public class Routes {

    @Bean
    public RouterFunction<ServerResponse> productServiceRoute(){

        return GatewayRouterFunctions.route("product_service")
                .route(RequestPredicates.path("/api/product"), HandlerFunctions.http("http://localhost:8080/api/product"))
                .route(RequestPredicates.GET("/api/product/*"), HandlerFunctions.http("http://localhost:8080/api/product/*"))
                .route(RequestPredicates.POST("/api/product/create"), HandlerFunctions.http("http://localhost:8080/api/product/create"))
                .route(RequestPredicates.DELETE("/api/product/*"), HandlerFunctions.http("http://localhost:8080/api/product/*"))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> orderServiceRoute(){

        return GatewayRouterFunctions.route("order_service")
                .route(RequestPredicates.path("/api/order"), HandlerFunctions.http("http://localhost:8081/api/order"))
                .route(RequestPredicates.POST("/api/order"), HandlerFunctions.http("http://localhost:8081/api/order"))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> inventoryServiceRoute(){

        return GatewayRouterFunctions.route("inventory_service")
                .route(RequestPredicates.path("/api/inventory"), HandlerFunctions.http("http://localhost:8082/api/inventory"))
                .route(RequestPredicates.GET("/api/inventory"), HandlerFunctions.http("http://localhost:8082/api/inventory"))
                .build();
    }
}
