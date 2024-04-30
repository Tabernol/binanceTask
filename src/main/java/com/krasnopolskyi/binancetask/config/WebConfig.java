package com.krasnopolskyi.binancetask.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

import java.time.Duration;

@Configuration
public class WebConfig {

    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    @Bean
    public HttpClient httpClient() {
        return HttpClient.create()
                .followRedirect(true)
                .responseTimeout(Duration.ofSeconds(3)); // Overall response timeout

    }

    @Bean
    public WebClient.Builder webClientBuilder(HttpClient httpClient) {
        return WebClient.builder()
                .clientConnector(
                        new ReactorClientHttpConnector(httpClient));
    }
}
