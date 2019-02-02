package com.zopa.http.api;

import ratpack.handling.Context;
import ratpack.handling.Handler;

public class ApplicationRootHandler implements Handler {

    @Override
    public void handle(Context ctx) {
        ctx.getResponse().send("Welcome to Zopa Loan Calculator!");
    }

}
