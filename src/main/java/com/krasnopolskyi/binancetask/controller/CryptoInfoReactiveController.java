package com.krasnopolskyi.binancetask.controller;

import com.krasnopolskyi.binancetask.model.CryptoInfo;
import com.krasnopolskyi.binancetask.service.CryptoInfoReactiveService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/crypto-info-reactive")
public class CryptoInfoReactiveController {

    private final CryptoInfoReactiveService cryptoInfoReactiveService;

    @GetMapping()
    public Flux<CryptoInfo> getAllCryptoInfoReactive() {
        return cryptoInfoReactiveService.getAll();
    }


    @GetMapping("/{cryptoPair}")
    public Mono<String> getPriceReactive(@PathVariable String cryptoPair) {
        return cryptoInfoReactiveService.getPrice(cryptoPair);
    }
}
