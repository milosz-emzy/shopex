package com.emzy.shopex.config;

import com.emzy.shopex.exceptions.ClientZipCodeNotFoundException;
import com.emzy.shopex.zippo.ZippoClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;
import reactor.core.publisher.Mono;

@Configuration
public class AppConfig {

    @Bean
    public ZippoClient zippoClient() {
        WebClient webClient = WebClient.builder()
                .baseUrl("https://api.zippopotam.us/")
                .defaultStatusHandler(HttpStatusCode::is4xxClientError, (response) ->
                        response.bodyToMono(String.class)
                                .flatMap(error -> Mono.error(new ClientZipCodeNotFoundException(error))))
                .defaultStatusHandler(HttpStatusCode::is5xxServerError, clientResponse ->
                        Mono.just(new RuntimeException(clientResponse.statusCode().toString()))
                )
                .build();
        HttpServiceProxyFactory serviceProxyFactory = HttpServiceProxyFactory
                .builderFor(WebClientAdapter.create(webClient))
                .build();

        return serviceProxyFactory.createClient(ZippoClient.class);
    }
}
