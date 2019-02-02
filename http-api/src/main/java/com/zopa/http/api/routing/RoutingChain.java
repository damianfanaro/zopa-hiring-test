package com.zopa.http.api.routing;

import com.zopa.http.api.ApplicationRootHandler;
import com.zopa.http.api.config.ZopaConfigHandler;
import com.zopa.http.api.lender.LenderHandler;
import com.zopa.http.api.loan.LoanHandler;
import ratpack.func.Action;
import ratpack.handling.Chain;

public class RoutingChain implements Action<Chain> {

    @Override
    public void execute(Chain chain) {
        chain.path(ApplicationRootHandler.class);
        chain.path("lender", LenderHandler.class);
        chain.path("loan", LoanHandler.class);
        chain.path("config", ZopaConfigHandler.class);
    }

}
