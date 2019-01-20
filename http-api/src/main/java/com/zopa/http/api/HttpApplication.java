package com.zopa.http.api;

import com.zopa.GreetingFormatter;

public class HttpApplication {
	
    public static void main(String[] args) {
        final String output = GreetingFormatter.greeting(args[0]);
        System.out.println(output);
    }

}
