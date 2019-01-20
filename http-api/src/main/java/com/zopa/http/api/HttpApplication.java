package com.zopa.http.api;

import ratpack.server.RatpackServer;
import ratpack.server.RatpackServerSpec;

public class HttpApplication {

    public static void main(String[] args) throws Exception {
        startServer();
    }

    private static void startServer() throws Exception {
        RatpackServer.start(HttpApplication::setHandlers);
    }

    private static RatpackServerSpec setHandlers(RatpackServerSpec server) {
        return server.handlers(chain -> chain
                .get(ctx -> ctx.render("Hello World!"))
                .get(":name", ctx -> ctx.render("Hello " + ctx.getPathTokens().get("name") + "!")));
    }

}
