package com.zopa.http.api.config;

import ratpack.handling.Context;
import ratpack.handling.Handler;
import ratpack.jackson.JsonRender;

import static ratpack.jackson.Jackson.json;

public class ZopaConfigHandler implements Handler {

    private final ZopaConfig zopaConfig;

    public ZopaConfigHandler(ZopaConfig zopaConfig) {
        this.zopaConfig = zopaConfig;
    }

    @Override
    public void handle(Context ctx) {
        JsonRender json = json(zopaConfig);
        ctx.render(json);
    }

}
