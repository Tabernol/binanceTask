package com.krasnopolskyi.binancetask.service;

import com.krasnopolskyi.binancetask.exception.CryptoPairNameException;
import com.krasnopolskyi.binancetask.model.CryptoInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class CryptoInfoReactiveService {
    private final WebClient.Builder webClientBuilder;

    private static final String URL = "https://testnet.binancefuture.com/fapi/v1/premiumIndex";

    public Flux<CryptoInfo> getAll() {
        return webClientBuilder.build()
                .get()
                .uri(URL)
                .retrieve()
                .bodyToFlux(CryptoInfo.class)
                .collectList() // Collect the stream into a list
                .flatMapMany(Flux::fromIterable); // Convert the list to a Flux
    }

    public Mono<String> getPrice(String cryptoPair) {
        return getAll()
                .filter(cryptoInfo -> cryptoPair.equals(cryptoInfo.getSymbol()))
                .next() // take the first element matching the filter
                .switchIfEmpty(Mono.error(new CryptoPairNameException("CryptoPair not found: " + cryptoPair)))
                .map(cryptoInfo -> cryptoPair + " = " + cryptoInfo.getMarkPrice());
    }
}
