package com.example;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class CamelRouter extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("netty4-http:http://0.0.0.0:8080/endpoint?httpMethodRestrict=POST")
                .transform().constant("Bye World");
    }
}
