package com.zopa.http.api.config;

import lombok.Data;

@Data
public class ZopaConfig {

    private int timePeriod;
    private int loanMultiplicity;
    private int minimumLoanAllowed;
    private int maximumLoanAllowed;

}
