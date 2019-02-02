package com.zopa.http.api;

import ratpack.error.ClientErrorHandler;
import ratpack.handling.Context;

public class GlobalErrorHandler implements ClientErrorHandler {

    @Override
    public void error(Context context, int statusCode) throws Exception {
        //context.clientError(500);
    }

}
