package com.zopa.http.api;

import com.zopa.http.api.config.ZopaConfig;
import com.zopa.http.api.routing.RoutingChain;
import com.zopa.http.api.wiring.ApplicationModule;
import com.zopa.http.api.wiring.RatpackModule;
import ratpack.guice.Guice;
import ratpack.server.BaseDir;
import ratpack.server.RatpackServer;

class RatpackBootstrapper {

    static void run() throws Exception {
        RatpackServer.start(server -> server
                .serverConfig(config -> config
                        .baseDir((BaseDir.find()))
                        .yaml("application.yml")
                        .require("/zopa", ZopaConfig.class))
                .registry(Guice.registry(bindings -> bindings
                        .module(ApplicationModule.class)
                        .module(RatpackModule.class)))
                .handlers(chain -> chain
                        .insert(RoutingChain.class)));
    }

}
