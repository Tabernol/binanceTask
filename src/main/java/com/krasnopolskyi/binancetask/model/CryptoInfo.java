package com.krasnopolskyi.binancetask.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CryptoInfo {
    private String symbol;
    private String markPrice;
    private String indexPrice;
    private String estimatedSettlePrice;
    private String lastFundingRate;
    private Long nextFundingTime;
    private String interestRate;
    private Long time;
}
