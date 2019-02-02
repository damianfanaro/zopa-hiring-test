package com.zopa.http.api.wiring;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.zopa.http.api.ApplicationRootHandler;
import com.zopa.http.api.Banner;
import com.zopa.http.api.GlobalErrorHandler;
import com.zopa.http.api.config.ZopaConfig;
import com.zopa.http.api.config.ZopaConfigHandler;
import com.zopa.http.api.lender.LenderHandler;
import com.zopa.http.api.loan.LoanHandler;
import com.zopa.http.api.routing.RoutingChain;
import com.zopa.loan.calculator.LenderDataProvider;
import com.zopa.loan.calculator.LowestRateCalculatorStrategy;
import com.zopa.loan.calculator.ZopaRateSystem;
import ratpack.error.ClientErrorHandler;

import javax.inject.Inject;
import javax.inject.Singleton;

public class RatpackModule extends AbstractModule {

    @Provides
    Banner banner() {
        return new Banner();
    }

    @Inject
    @Provides
    LenderHandler lenderHandler(LenderDataProvider lenderDataProvider) {
        return new LenderHandler(lenderDataProvider);
    }

    @Provides
    ApplicationRootHandler applicationRootHandler() {
        return new ApplicationRootHandler();
    }

    @Provides
    RoutingChain routingChain() {
        return new RoutingChain();
    }

    @Provides
    ClientErrorHandler clientErrorHandler() {
        return new GlobalErrorHandler();
    }

    @Inject
    @Provides
    LoanHandler loanCalculatorHandler(ZopaRateSystem zopaRateSystem) {
        return new LoanHandler(zopaRateSystem, new LowestRateCalculatorStrategy());
    }

    @Provides
    @Singleton
    ZopaConfigHandler zopaConfigHandler(ZopaConfig zopaConfig) {
        return new ZopaConfigHandler(zopaConfig);
    }

}
