package com.krasnopolskyi.binancetask.controller;

import com.krasnopolskyi.binancetask.exception.CryptoPairNameException;
import com.krasnopolskyi.binancetask.model.CryptoInfo;
import com.krasnopolskyi.binancetask.service.CryptoInfoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.Set;


@RestController
@RequiredArgsConstructor
@RequestMapping("/crypto-info")
@Slf4j
public class CryptoInfoController {
    private final CryptoInfoService cryptoInfoService;

    @GetMapping
    public ResponseEntity<Set<CryptoInfo>> getAllCryptoInfo() {
        Set<CryptoInfo> cryptoInfoList = new HashSet<>(cryptoInfoService.getAll().values());
        return ResponseEntity.status(HttpStatus.OK).body(cryptoInfoList);
    }

    @GetMapping("/{cryptoPair}/")
    public ResponseEntity<String> getPrice(@PathVariable("cryptoPair") String cryptoPair)
            throws CryptoPairNameException {
        String pair = cryptoInfoService.getPrice(cryptoPair);
        return ResponseEntity.status(HttpStatus.OK).body(pair);
    }
}
