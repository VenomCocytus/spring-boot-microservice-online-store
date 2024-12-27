package com.sehkmet.client.inventory.config;

import com.sehkmet.client.inventory.client.InventoryClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.http.client.ClientHttpRequestFactoryBuilder;
import org.springframework.boot.http.client.ClientHttpRequestFactorySettings;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import java.time.Duration;

@Configuration
public class RestClientConfig {

    @Value("${inventory.url}")
    private String inventoryServiceUrl;

    @Bean
    public InventoryClient inventoryClient() {
        RestClient restClient = RestClient.builder()
                .baseUrl(inventoryServiceUrl)
//                .requestFactory(getClientHttpRequestFactory())
                .build();

        var restClientAdapter = RestClientAdapter.create(restClient);
        var httpServiceProxyFactory = HttpServiceProxyFactory.builderFor(restClientAdapter).build();

        return httpServiceProxyFactory.createClient(InventoryClient.class);
    }

    // To add a timeout to the response of the client
//    private ClientHttpRequestFactory getClientHttpRequestFactory() {
//        ClientHttpRequestFactorySettings clientHttpRequestFactorySettings =
//                ClientHttpRequestFactorySettings
//                        .defaults()
//                        .withConnectTimeout(Duration.ofSeconds(3))
//                        .withReadTimeout(Duration.ofSeconds(3));
//
//        return ClientHttpRequestFactoryBuilder.simple().build(clientHttpRequestFactorySettings);
//    }
}
