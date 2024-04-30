package com.krasnopolskyi.binancetask.service;

import com.krasnopolskyi.binancetask.exception.CryptoPairNameException;
import com.krasnopolskyi.binancetask.model.CryptoInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class CryptoInfoService {
    private final RestTemplate restTemplate;

    private static final String URL = "https://testnet.binancefuture.com/fapi/v1/premiumIndex";

    public Map<String, CryptoInfo> getAll() {
        ResponseEntity<Set<CryptoInfo>> response = restTemplate.exchange(
                URL,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {
                }
        );
        Map<String, CryptoInfo> cryptoInfoMap = new HashMap<>();
        Set<CryptoInfo> body = response.getBody();
        if (body != null) {
            body.forEach(cryptoInfo -> cryptoInfoMap.put(cryptoInfo.getSymbol(), cryptoInfo));
        }
        return cryptoInfoMap;
    }

    public String getPrice(String cryptoPair) throws CryptoPairNameException {
        Map<String, CryptoInfo> cryptoInfoMap = getAll();
        CryptoInfo cryptoInfo = cryptoInfoMap.get(cryptoPair);
        validateCryptoPairName(cryptoInfo); // Validate name of cryptoPair
        String markPrice = cryptoInfoMap.get(cryptoPair).getMarkPrice();
        return cryptoPair + " = " + markPrice;
    }

    private void validateCryptoPairName(CryptoInfo cryptoInfo) throws CryptoPairNameException {
        if (cryptoInfo == null) {
            throw new CryptoPairNameException("CryptoPair not found.");
        }
    }

}
