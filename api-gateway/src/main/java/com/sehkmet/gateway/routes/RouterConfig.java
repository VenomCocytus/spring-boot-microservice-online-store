package com.sehkmet.gateway.routes;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

import static com.sehkmet.utils.utils.Utils.translate;
import static org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions.route;

@Configuration
public class RouterConfig {

    @Bean
    public RouterFunction<ServerResponse> fallbackRoute() {

        return route("fallbackRoute")
                .GET("/fallbackRoute",
                        request -> ServerResponse
                                .status(HttpStatus.SERVICE_UNAVAILABLE)
                                .body(translate("circuit.breaker.service.unavailable")))
                .build();
    }
}
