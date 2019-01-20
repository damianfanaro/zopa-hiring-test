package com.zopa.http.api;

import ratpack.server.RatpackServer;

public class HttpApplication {

    public static void main(String[] args) throws Exception {
        startServer();
    }

    private static void startServer() throws Exception {
        RatpackServer.start(server -> server.handlers(chain -> chain

                .get(ctx -> ctx.render("Welcome to Zopa Loan Calculator!"))

        ));
    }

}
