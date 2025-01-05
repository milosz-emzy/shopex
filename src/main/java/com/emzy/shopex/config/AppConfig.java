package com.emzy.shopex.config;

import com.emzy.shopex.zippo.ZippoClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class AppConfig {

    @Bean
    public ZippoClient zippoClient() {
        WebClient webClient = WebClient.builder()
                .baseUrl("https://api.zippopotam.us/")
                .build();
        HttpServiceProxyFactory serviceProxyFactory = HttpServiceProxyFactory
                .builderFor(WebClientAdapter.create(webClient))
                .build();

        return serviceProxyFactory.createClient(ZippoClient.class);
    }
}
