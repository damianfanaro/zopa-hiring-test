package com.zopa.http.api.lender;

import com.zopa.loan.calculator.LenderDataProvider;
import ratpack.handling.Context;
import ratpack.handling.Handler;
import ratpack.jackson.JsonRender;

import static ratpack.jackson.Jackson.json;

public class LenderHandler implements Handler {

    private final LenderDataProvider lenderDataProvider;

    public LenderHandler(LenderDataProvider lenderDataProvider) {
        this.lenderDataProvider = lenderDataProvider;
    }

    @Override
    public void handle(Context ctx) {
        JsonRender json = json(lenderDataProvider.getLenders());
        ctx.render(json);
    }

}
