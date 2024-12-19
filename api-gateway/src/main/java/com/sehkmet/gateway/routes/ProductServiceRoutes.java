package com.sehkmet.gateway.routes;

import org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions;
import org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RequestPredicates;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

import java.net.URI;

import static org.springframework.cloud.gateway.server.mvc.filter.CircuitBreakerFilterFunctions.circuitBreaker;
import static org.springframework.cloud.gateway.server.mvc.filter.FilterFunctions.setPath;

@Configuration
public class ProductServiceRoutes {

    @Bean
    public RouterFunction<ServerResponse> productServiceApiRoute() {

        return GatewayRouterFunctions.route("product_service")
                .route(RequestPredicates.path("/api/product"), HandlerFunctions.http("http://localhost:8080/api/product"))
                .route(RequestPredicates.GET("/api/product/*"), HandlerFunctions.http("http://localhost:8080/api/product/*"))
                .route(RequestPredicates.POST("/api/product/create"), HandlerFunctions.http("http://localhost:8080/api/product/create"))
                .route(RequestPredicates.DELETE("/api/product/*"), HandlerFunctions.http("http://localhost:8080/api/product/*"))

                .filter(circuitBreaker("productServiceApiCircuitBreaker", URI.create("forward:/fallbackRoute")))

                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> productServiceSwaggerRoute() {

        return GatewayRouterFunctions.route("product_service_swagger")
                .route(RequestPredicates.path("/aggregate/product-service/v3/api-docs"), HandlerFunctions.http("http://localhost:8080/api/product"))
                .filter(setPath("/api-docs"))

                .filter(circuitBreaker("productServiceSwaggerCircuitBreaker", URI.create("forward:/fallbackRoute")))

                .build();
    }
}
